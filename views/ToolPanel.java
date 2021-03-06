/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Supervisor;
import java.util.List;
import models.MainContainer;
import models.postprocessing.PostProcessor;

/**
 *
 * @author Kat
 */
public class ToolPanel extends javax.swing.JPanel {

    /**
     * Creates new form ToolPanel
     */
    public ToolPanel() {
        initComponents();
        MainContainer.setStartAndStopButton(startAndStopButton);
    }

    public void setSpeedCaption(String speed) {
        //speedLabel.setText(speed);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startAndStopButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setMaximumSize(new java.awt.Dimension(300, 60));

        startAndStopButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        startAndStopButton.setText("Start");
        startAndStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAndStopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startAndStopButton, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startAndStopButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startAndStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startAndStopButtonActionPerformed
        if (startAndStopButton.getText().equals("Start")) {
            try {
                //TODO знаю что решение кривое. исправить
                Supervisor.startThatProcess();
                if (MainContainer.getDataSource() == MainContainer.REAL_DEVICE) {
                    MainContainer.getMainFrame().connectMenuItemActionPerfomed(evt);
                }
                MainContainer.getMainFrame().menuItemStartActionPerformed(evt);
                startAndStopButton.setText("Stop");

                MainContainer.getMainFrame().setMeasurementCount("1");
                MainContainer.setCurrentState(MainContainer.SPEED_LOWER_THAN_MIN);

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }

        } else {
            MainContainer.getMainFrame().menuItemStopActionPerformed(evt);
            startAndStopButton.setText("Start");

            List<Double> accelerationAndDecelerationTime = PostProcessor.getAccelerationAndDecelerationTime(
                    MainContainer.getListMeasurements().get(
                    MainContainer.getListMeasurements().size() - 1).getListNeedfulSpeedsData());

            double accelerationTimeResult = 0L;//(accelerationTime1+accelerationTime2+accelerationTime3)/3;
            double decelerationTimeResult = 0L;//(decelerationTime1+decelerationTime2+decelerationTime3)/3;

            accelerationTimeResult = accelerationAndDecelerationTime.get(0);
            decelerationTimeResult = accelerationAndDecelerationTime.get(1);

            System.out.println("время разгона итоговое:" + accelerationTimeResult);
            System.out.println("время выбега итоговое:" + decelerationTimeResult);

            MainContainer.getMainFrame().setAccelerateTimeCaption(accelerationTimeResult);
            MainContainer.getMainFrame().setDecelerateTimeCaption(decelerationTimeResult);

            MainContainer.getMainFrame().setMeasurementCount("-");
            MainContainer.setCurrentState(MainContainer.SPEED_LOWER_THAN_MIN);
            MainContainer.setMeasurementCount(1);
            
            MainContainer.getMainFrame().getLeftIndicationPanel().disableAllPanels();
        }


    }//GEN-LAST:event_startAndStopButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton startAndStopButton;
    // End of variables declaration//GEN-END:variables
}
