package della.swaf.application.application;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.sourcesense.stuff.event.Event;
import com.sourcesense.stuff.event.EventFactory;

import della.swaf.gui.util.PanelMediator;

public class ApplicationWindowBuilder {

   protected DefaultApplicationWindow appWindow;

   protected Application application;

   public void loadWindow(final DefaultApplication application) {

      this.application = application;

      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      appWindow.setFrame(frame);

      // appWindow.setNewLookAndFeel(new NimbusLookAndFeel());
      appWindow.setNewLookAndFeel(new Plastic3DLookAndFeel());

   }

   protected void createWindow() {
      appWindow = new DefaultApplicationWindow();
   }

   protected DefaultApplicationWindow getApplicationWindow() {
      return appWindow;
   }

   protected void initMainWindow() {
      appWindow.useTopPanel();
      appWindow.useBottonPanel();

      JPanel perspectiveRelatedComponentPanel = new JPanel();
      PanelMediator perspectivePanelMediator = new PanelMediator(perspectiveRelatedComponentPanel);
      appWindow.setPerspectivePanelMediator(perspectivePanelMediator);

      JPanel applicationRelatedComponentPanel = new JPanel();
      appWindow.setApplicationRelatedComponentPanel(applicationRelatedComponentPanel);

      File saveFile = new File(application.getConfigFolder() + File.separator + "frame.xml");
      appWindow.enablePersistence(saveFile);

      appWindow.addWindowListener(new WindowAdapter() {

         public void windowClosed(WindowEvent e) {
            Event ge = EventFactory.createEvent(this, Application.CLOSED);
            application.fireEvent(ge);
         }

         public void windowClosing(WindowEvent e) {
            Event ge = EventFactory.createEvent(this, Application.CLOSING);
            application.fireEvent(ge);
         }
      });

   }

   protected void showMainWindow() {
      if (!appWindow.isVisible())
         appWindow.revalidateAndRepaint();
      appWindow.setVisible(true);
   }

   protected void postLoading() {
   }

   public DefaultApplicationWindow getAppWindow() {
      return appWindow;
   }

}
