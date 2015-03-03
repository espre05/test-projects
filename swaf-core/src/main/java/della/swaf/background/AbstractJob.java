package della.swaf.background;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import della.swaf.background.swing.SwingBackgroundTask;

public abstract class AbstractJob {

   private JProgressBar progressBar;

   private JLabel feedbackLabel;

   private final String genericElementsTypeText;

   // protected abstract void run();

   public AbstractJob() {
      genericElementsTypeText = "elements";
      // TODO Auto-generated constructor stub
   }

   public final void setProgressBar(JProgressBar bar) {
      this.progressBar = bar;
   }

   public final JProgressBar getProgressBar() {
      return progressBar;
   }

   public final JLabel getFeedbackLabel() {
      return feedbackLabel;
   }

   public final void setFeedbackLabel(JLabel feedbackLabel) {
      this.feedbackLabel = feedbackLabel;
   }

   protected abstract TaskList getThreadPool();

   /**
    * 
    * @deprecated
    */
   protected void attachStandardListenersTo(SwingBackgroundTask task, String labelText) {
      attachStandardListenersTo(task, labelText, false, genericElementsTypeText);
   }

   /**
    * 
    * @deprecated
    */
   protected void attachStandardListenersTo(SwingBackgroundTask task, String labelText,
         String elementsTypeText) {
      attachStandardListenersTo(task, labelText, false, elementsTypeText);
   }

   /**
    * 
    * @deprecated
    */
   protected void attachStandardListenersTo(SwingBackgroundTask task, String labelText,
         boolean indeterminateProgressBar) {
      attachStandardListenersTo(task, labelText, indeterminateProgressBar, genericElementsTypeText);
   }

   /**
    * 
    * @deprecated
    */
   protected void attachStandardListenersTo(SwingBackgroundTask task, String labelText,
         boolean indeterminateProgressBar, String elementsTypeText) {
      task.addPropertyChangeListener(TaskListenerFactory.createProgressBarListener(getProgressBar(),
            indeterminateProgressBar, elementsTypeText));
      task.addPropertyChangeListener(TaskListenerFactory.createFeedbackLabelListener(getFeedbackLabel(),
            labelText));
   }

   public static AbstractJob newSimpleJob(final BackgroundTask task, final String labelText,
         final String elementsType) {
      return new AbstractJob() {

         protected TaskList getThreadPool() {
            TaskList threadPool = TaskList.simpleThreadPool();
            threadPool.add(task);
            task.setName(labelText);
            task.setElementsType(elementsType);
            // attachStandardListenersTo(task, labelText, elementsType);
            return threadPool;
         }
      };
   }

   public static AbstractJob newScheduledJob(final BackgroundTask task, int delay) {
      return new AbstractJob() {

         protected TaskList getThreadPool() {
            TaskList threadPool = TaskList.simpleThreadPool();
            threadPool.add(task);
            return threadPool;
         }
      };
   }

   public static AbstractJob newSimpleJob(final TaskList pool) {
      return new AbstractJob() {
         protected TaskList getThreadPool() {
            return pool;
         }
      };
   }

}