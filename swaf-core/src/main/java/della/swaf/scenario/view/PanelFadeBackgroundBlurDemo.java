package della.swaf.scenario.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import com.sun.scenario.animation.Clip;
import com.sun.scenario.animation.Composer;
import com.sun.scenario.scenegraph.*;

import della.swaf.scenario.ClipFactory;
import della.swaf.scenario.FaderBuilder;
import della.swaf.scenario.NodeFader;
import della.swaf.scenario.node.TransformComposer;

public class PanelFadeBackgroundBlurDemo implements ActionListener {

   private static final int SCREEN_W = 500, SCREEN_H = 400;
   private static final int FADE_DUR = 500;
   private static JFrame frame;
   private static SGGroup rootNode;
   private static NodeFader mainFader;
   private static NodeFader overlayFader;
   private static Clip componentScaleIn;

   static {
      Composer.register(AffineTransform.class, TransformComposer.class);
   }

   public static void main(String[] args) {
      Runnable doCreateAndShowGUI = new Runnable() {
         public void run() {
            new PanelFadeBackgroundBlurDemo().start();
         }
      };
      SwingUtilities.invokeLater(doCreateAndShowGUI);
   }

   private NodeFader backgroundFader;

   protected void start() {
      frame = new JFrame("Fade And Blur Demo");
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
         System.out.println("problem with l&f: " + e);
      }
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      rootNode = new SGGroup();

      setupBackground();
      setupOverlayPanel();
      setupMainPanel();
      setUpGradientPanel();

      JSGPanel sgPanel = new JSGPanel();
      sgPanel.setOpaque(true);
      sgPanel.setBackground(Color.ORANGE.brighter());
      sgPanel.setScene(rootNode);
      sgPanel.setPreferredSize(new Dimension(SCREEN_W, SCREEN_H));
      JPanel mainPanel = new JPanel(new BorderLayout());
      frame.add(mainPanel);
      // frame.add(sgPanel);
      JPanel topPanel = new JPanel();
      topPanel.add(new JLabel("This panel is not rendered by scenegraph"));
      mainPanel.add(topPanel, BorderLayout.NORTH);
      mainPanel.add(sgPanel, BorderLayout.CENTER);

      frame.invalidate();
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   private void setUpGradientPanel() {
      // GradientPanel gradientPanel = new GradientPanel(Color.BLUE);
      // label.setOpaque(false);
      //
      // gradientPanel.add(label);
      // gradientPanel.setBorder(BorderFactory.createEmptyBorder(3, 4, 3, 1));
      //
      // JPanel headerPanel = new JPanel();
      // setToolBar(bar);
      // headerPanel.setBorder(new RaisedHeaderBorder());
      // headerPanel.setOpaque(false);
      // SGGroup topGroup = new SGGroup();
      //
      // SGComponent gradientNode = new SGComponent();
      // gradientNode.setComponent(gradientPanel);
      // SGComponent headerNode = new SGComponent();
      // headerNode.setComponent(headerPanel);
      //
      // topGroup.add(gradientNode);
      // topGroup.add(headerNode);
      //      
      // rootNode.add(topGroup);
   }

   private void setupBackground() {
      SGShape bgNode = new SGShape();
      Rectangle2D bgRect = new Rectangle2D.Double(0, 0, SCREEN_W, SCREEN_H);
      bgNode.setShape(bgRect);

      // Setup gradient for background
      Point2D start = new Point2D.Float(0, 0);
      Point2D end = new Point2D.Float(0, SCREEN_H);
      float[] dist = { 0.0f, 0.46f, 0.48f, 1.0f };
      Color[] colors = { new Color(228, 244, 250), new Color(157, 219, 236), new Color(87, 201, 224),
            new Color(186, 228, 240) };
      LinearGradientPaint p = new LinearGradientPaint(start, end, dist, colors);
      bgNode.setMode(SGShape.Mode.FILL);
      bgNode.setFillPaint(p);
      bgNode.setVisible(false);

      float minOpacity = 0f;
      float maxOpacity = .7f;
      SGComposite faderNode = new SGComposite();
      faderNode.setOpacity(minOpacity);
      faderNode.setChild(bgNode);
      FaderBuilder faderBuilder = new FaderBuilder(FADE_DUR, minOpacity, maxOpacity);
      Clip clipIn = ClipFactory.makeVisible(bgNode);
      clipIn.addEndAnimation(faderBuilder.fadeIn(faderNode));
      Clip clipOut = faderBuilder.fadeOut(faderNode);
      clipOut.addEndAnimation(ClipFactory.makeNonVisible(bgNode));
      backgroundFader = new NodeFader(clipIn, clipOut);

      rootNode.add(faderNode);
   }

   private void setupOverlayPanel() {
      JPanel overlayPanel = new JPanel();
      overlayPanel.add(new JLabel("search: "));
      overlayPanel.add(new JTextField("type text here..."));
      overlayPanel.add(new JButton("Search"));
      overlayPanel.setBackground(Color.BLACK);
      overlayPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE));

      SGComponent componentNode = new SGComponent();
      componentNode.setComponent(overlayPanel);

      AffineTransform cpScaleLarge = AffineTransform.getTranslateInstance(30, 60);
      cpScaleLarge.scale(1.5d, 1.5d);
      SGTransform componentScaler = SGTransform.createAffine(cpScaleLarge, componentNode);
      componentScaleIn = Clip.create(500, componentScaler, "affine", cpScaleLarge);

      float minOpacity = 0f;
      float maxOpacity = .7f;
      SGComposite faderNode = new SGComposite();
      faderNode.setOpacity(minOpacity);
      faderNode.setChild(componentScaler);
      FaderBuilder faderBuilder = new FaderBuilder(FADE_DUR, minOpacity, maxOpacity);
      Clip clipIn = ClipFactory.makeVisible(componentNode);
      clipIn.addEndAnimation(faderBuilder.fadeIn(faderNode));
      Clip clipOut = faderBuilder.fadeOut(faderNode);
      clipOut.addEndAnimation(ClipFactory.makeNonVisible(componentNode));
      overlayFader = new NodeFader(clipIn, clipOut);

      rootNode.add(faderNode);
   }

   private void setupMainPanel() {
      JPanel mainPanel = new JPanel();
      BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
      mainPanel.setLayout(boxLayout);
      mainPanel.add(new JLabel("Main application Window: "));
      JButton button = new JButton("Click To Show Overlay Panel!");
      button.addActionListener(this);
      mainPanel.add(button);
      mainPanel.setBackground(Color.CYAN);

      SGComponent componentNode = new SGComponent();
      componentNode.setComponent(mainPanel);

      float minOpacity = .2f;
      float maxOpacity = 1f;
      SGComposite faderNode = new SGComposite();
      faderNode.setOpacity(maxOpacity);
      faderNode.setChild(componentNode);
      FaderBuilder faderBuilder = new FaderBuilder(FADE_DUR, minOpacity, maxOpacity);
      Clip clipIn = ClipFactory.makeVisible(componentNode);
      clipIn.addEndAnimation(faderBuilder.fadeIn(faderNode));
      Clip clipOut = faderBuilder.fadeOut(faderNode);
      mainFader = new NodeFader(clipIn, clipOut);
      mainFader.setVisible(true);

      rootNode.add(faderNode);
   }

   public void actionPerformed(ActionEvent arg0) {
      System.out.println("click!");
      mainFader.toggle();
      overlayFader.toggle();
      componentScaleIn.start();
      backgroundFader.toggle();
   }
}
