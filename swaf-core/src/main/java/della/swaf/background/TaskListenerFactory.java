package della.swaf.background;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import della.swaf.background.swing.IndeterminateBackgroundTask;
import della.swaf.background.swing.SwingBackgroundTask;

public class TaskListenerFactory {

   private static final class ProgressBarListener implements PropertyChangeListener {

      private JProgressBar progressBar;

      private String max = "";

      private boolean indeterminate = false;

      private String elementsTypeText;

      public ProgressBarListener(JProgressBar progressBar) {
         this.progressBar = progressBar;
      }

      public void setIndeterminate(boolean indeterminate) {
         this.indeterminate = indeterminate;
      }

      public void setMax(int max) {
         this.max = Integer.toString(max);
      }

      public void propertyChange(PropertyChangeEvent evt) {
         if ("state".equals(evt.getPropertyName())) {
            Object newValue = evt.getNewValue();
            // log.info("scan for files " + newValue);
            if (SwingWorker.StateValue.STARTED.equals(newValue)) {
               progressBar.setIndeterminate(indeterminate);
            }
            if (SwingWorker.StateValue.DONE.equals(newValue)) {
               progressBar.setIndeterminate(false);
               progressBar.setString("");
               progressBar.setValue(0);
            }
         }
         if ("progress".equals(evt.getPropertyName())) {
            Integer newValue = (Integer) evt.getNewValue();
            progressBar.setValue(newValue);
            progressBar.setString(newValue + "% of " + max + " " + elementsTypeText);
         }
         if ("iterationState".equals(evt.getPropertyName())) {
            Integer newValue = (Integer) evt.getNewValue();
            progressBar.setString(newValue + " out of " + max + " " + elementsTypeText);
         }
         if ("max".equals(evt.getPropertyName())) {
            Integer newValue = (Integer) evt.getNewValue();
            max = Integer.toString(newValue);
         }
         if (IndeterminateBackgroundTask.INDETERMINATE.equals(evt.getPropertyName())) {
            progressBar.setIndeterminate((Boolean) evt.getNewValue());
         }
         if (SwingBackgroundTask.ELEMENTS_TYPE.equals(evt.getPropertyName())) {
            elementsTypeText = (String) evt.getNewValue();
         }

      }

      public void setElementsTypeText(String elementsTypeText) {
         this.elementsTypeText = elementsTypeText;
      }
   }

   private static final class FeedbackLabelListener implements PropertyChangeListener {
      private String feedbackLabelText;

      JLabel feedbackLabel;

      public FeedbackLabelListener(JLabel feedbackLabel, String text) {
         this.feedbackLabelText = text;
         this.feedbackLabel = feedbackLabel;
      }

      public FeedbackLabelListener(JLabel userFeedbackLabel) {
         feedbackLabelText = "";
         this.feedbackLabel = userFeedbackLabel;
      }

      public void propertyChange(PropertyChangeEvent evt) {
         if ("state".equals(evt.getPropertyName())) {
            Object newValue = evt.getNewValue();
            if (SwingWorker.StateValue.STARTED.equals(newValue)) {
               feedbackLabel.setForeground(Color.RED);
               feedbackLabel.setText(feedbackLabelText);
            }
            if (SwingWorker.StateValue.DONE.equals(newValue)) {
               feedbackLabel.setForeground(Color.BLACK);
               feedbackLabel.setText("");
            }
         }
         if (SwingBackgroundTask.NAME.equals(evt.getPropertyName())) {
            feedbackLabelText = (String) evt.getNewValue();
            feedbackLabel.setText(feedbackLabelText);
         }
      }
   }

   public static PropertyChangeListener createProgressBarListener(JProgressBar progressBar) {
      return new ProgressBarListener(progressBar);
   }

   public static PropertyChangeListener createProgressBarListener(JProgressBar progressBar,
         boolean indeterminate, String elementsTypeText) {
      ProgressBarListener progressBarListener = new ProgressBarListener(progressBar);
      progressBarListener.setIndeterminate(indeterminate);
      progressBarListener.setElementsTypeText(elementsTypeText);
      return progressBarListener;
   }

   public static PropertyChangeListener createFeedbackLabelListener(JLabel FeedbackLabel, String text) {
      return new FeedbackLabelListener(FeedbackLabel, text);
   }

   public static PropertyChangeListener createFeedbackLabelListener(JLabel userFeedbackLabel) {
      return new FeedbackLabelListener(userFeedbackLabel);
   }

}
