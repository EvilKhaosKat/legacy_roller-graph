/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Kat
 */
public class IndicationPanel extends javax.swing.JPanel {

    List<InnerPanelInterface> innerPanelList = new ArrayList();
    
    /**
     * Creates new form IndicationPanel
     */
    public IndicationPanel() {
        initComponents();
        BoxLayout tempBoxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(tempBoxLayout);
    }

    public void addElementPanel(InnerPanelInterface innerPanel) {
        //this.removeAll();
        //TODO с преобразанием типов, интерфейсами и прочим Я знатно нашаманил
        add((JPanel)innerPanel);
        innerPanelList.add(innerPanel);
        revalidate();
        //repaint();
        //TODO возможно нужно шаманство с ревалидацией/перерисовкой. не помню логику работы этого места. возможно требует доработки
        
    }
    
    public List<InnerPanelInterface> getInnerPanelList() {
        return innerPanelList;
    }
    
    public void clearPanel() {
        removeAll();
        revalidate();
        innerPanelList.clear();
    }
    
    //TODO ибо я layout делаю свой то в этом сгенерированном коде смысла нет. удалить надо бы. 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(100, 200));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
