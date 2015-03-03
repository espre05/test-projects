/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package harmonyfx.dndListener;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.Observable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Vector;

public class DnDListener extends Observable implements DropTargetListener {

    protected boolean acceptableType;
    public String[] files;

    public DnDListener() {
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        checkTransferType(dtde);
        acceptOrRejectDrag(dtde);
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        acceptOrRejectDrag(dtde);
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
          dtde.acceptDrop(dtde.getDropAction());
          Transferable transferable = dtde.getTransferable();

          try {
                boolean result = dropFile(transferable);
                dtde.dropComplete(result);
          } catch (Exception e) {
                dtde.dropComplete(false);
                System.out.print("exception: "+e.getMessage());
          }
        }else {
          dtde.rejectDrop();
        }
    }

    protected boolean acceptOrRejectDrag(DropTargetDragEvent dtde){
        int dropAction = dtde.getDropAction();
        int sourceActions = dtde.getSourceActions();
        boolean acceptedDrag = false;

        if (!acceptableType || (sourceActions & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            dtde.rejectDrag();
        } else if ((dropAction & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY);
            acceptedDrag = true;
        } else {
            dtde.acceptDrag(dropAction);
            acceptedDrag = true;
        }

        return acceptedDrag;
    }

    protected void checkTransferType(DropTargetDragEvent dtde) {
        acceptableType = dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor);

    }

    protected boolean dropFile(Transferable transferable) throws IOException,
        UnsupportedFlavorException, MalformedURLException {

        List fileList = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
        int i = 0;
        List<String> list = new Vector<String>();
        String[] fls = new String[fileList.size()];
        for (Object f : fileList){
            File file = (File) f;
            exploreDir(file, list);
//            if (file.isFile()){
//                if (file.getName().toLowerCase().endsWith(".mp3")){
//                    //fls[i++] = file.toURL().toString();
//                    list.add(file.toURL().toString());
//                }
//            }else if (file.isDirectory()){
////                File fs[] = file.listFiles();
////                for (File fd : fs){
////
////                }
//            }
        }
        
        if (list != null)
            setFiles(list);
        return true;
    }

    void exploreDir(File file, List<String> list){
        if (file.isFile()){
            if (file.getName().toLowerCase().endsWith(".mp3")){
                try{
                    list.add(file.toURL().toString());
                }catch(Exception ex){
                    System.out.println("Error conversion: "+ex.getMessage());
                }
            }
        }else if (file.isDirectory()){
            File fs[] = file.listFiles();
            for (File f : fs)
                exploreDir(f, list);
        }

    }

    public void setFiles(List<String> f){
        files = new String[f.size()];
        int i=0;
        for (String s: f)
            files[i++] = s;
        
        startNotification();
    }

    private void startNotification(){
        setChanged();
        notifyObservers();
    }
    
    public String[] getFiles(){
        return files;
    }
}