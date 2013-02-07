package views;

import java.awt.Color;

/**
 *
 * @author Kat
 */
public class InnerPanel extends javax.swing.JPanel implements InnerPanelInterface {

    protected Color enabledBackgroundColor = new Color(153, 255, 153);
    protected Color disabledBackgroundColor = new Color(240, 240, 240);
    protected int status = DISABLED;
    public static int DISABLED = 0;
    public static int ENABLED = 1;
    /* TODO панель сама знает какая она по номеру, эту информацию она отдаст супервизору, чтобы тот мог принять решение что же делать
     * идея и реализация в данном виде больше похожи на костыль, надо найти более эффективный способ
     */
    protected int number = 0;

    @Override
    public void setEnabled() {
        setBackground(enabledBackgroundColor);
        status = ENABLED;
    }

    @Override
    public void setDisabled() {
        //стандартный фоновый цвет (среди констант его почему-то не нашёл)
        setBackground(disabledBackgroundColor);
        status = DISABLED;
    }

    @Override
    public void setPanelNumber(int n) {
        number = n;
        //System.out.println("Settting nubmer to "+n);
    }

    @Override
    public int getPanelNumber() {
        return number;
    }

    @Override
    public int getStatus() {
        return status;
    }

    /**
     * Creates new form InnerPanel
     */
    public InnerPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}