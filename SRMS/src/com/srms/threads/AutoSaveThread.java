package com.srms.threads;
import com.srms.managers.RecordManager;

public class AutoSaveThread extends Thread {
    private RecordManager manager;
    private String filename;

    public AutoSaveThread(RecordManager manager, String filename) {
        this.manager = manager;
        this.filename = filename;
    }

    @Override
    public void run() {
            try {
        Thread.sleep(5000); // انتظر 5 ثواني قبل أول حفظ
         } catch(Exception e){}
        while (true) {
            try {
                manager.saveToFile(filename);
                System.out.println("[AUTO-SAVE DONE]");
                Thread.sleep(60000); // auto-save every 60 seconds
            } catch (Exception e) {
                System.out.println("Auto-save error: " + e.getMessage());
            }
        }
    }
}