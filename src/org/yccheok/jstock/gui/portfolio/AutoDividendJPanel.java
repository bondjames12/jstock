/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2013 Yan Cheng Cheok <yccheok@yahoo.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.yccheok.jstock.gui.portfolio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import org.yccheok.jstock.portfolio.Dividend;

/**
 *
 * @author yccheok
 */
public class AutoDividendJPanel extends javax.swing.JPanel {

    /**
     * Creates new form AutoDividendJPanel
     */
    public AutoDividendJPanel(AutoDividendJDialog autoDividendJDialog, List<Dividend> dividends) {
        initComponents();
        
        assert(false == dividends.isEmpty());
        
        this.autoDividendJDialog = autoDividendJDialog;
        
        jCheckBox2.setText(dividends.get(0).stockInfo.symbol.toString());
        
        for (Dividend dividend : dividends) {
            AutoDividendRowJPanel autoDividendRowJPanel = new AutoDividendRowJPanel(this, dividend);
            autoDividendRowJPanels.add(autoDividendRowJPanel);;
            this.jPanel2.add(autoDividendRowJPanel);
        }
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public List<Dividend> getSelectedDividends() {
        if (false == jCheckBox2.isSelected()) {
            return java.util.Collections.emptyList();
        }
        final List<Dividend> dividends = new ArrayList<Dividend>();
        for (AutoDividendRowJPanel autoDividendRowJPanel : autoDividendRowJPanels) {
            if (autoDividendRowJPanel.isSelected()) {
                if (autoDividendRowJPanel.getAmount() > 0.0) {
                    dividends.add(autoDividendRowJPanel.getDividend());
                }
            }
        }
        return dividends;
    }
    
    public double getSelectedAmount() {
        if (false == jCheckBox2.isSelected()) {
            return 0;
        }
        double amount = 0;
        for (AutoDividendRowJPanel autoDividendRowJPanel : autoDividendRowJPanels) {
            if (autoDividendRowJPanel.isSelected()) {
                amount += autoDividendRowJPanel.getAmount();
            }
        }        
        return amount;        
    }
    
    public int getSelectedCount() {
        if (false == jCheckBox2.isSelected()) {
            return 0;
        }
        int count = 0;
        for (AutoDividendRowJPanel autoDividendRowJPanel : autoDividendRowJPanels) {
            if (autoDividendRowJPanel.isSelected()) {
                count++;
            }
        }        
        return count;
    }
    
    public boolean isSelected() {
        if (false == jCheckBox2.isSelected()) {
            return false;
        }
        for (AutoDividendRowJPanel autoDividendRowJPanel : autoDividendRowJPanels) {
            if (autoDividendRowJPanel.isSelected()) {
                return true;
            }
        }        
        return false;
    }
    
    public void updateTotalLabel() {
        this.autoDividendJDialog.updateTotalLabel();
    }
    
    public void updateJCheckBoxColor() {
        if (isSelected()) {
            jCheckBox2.setBackground(GREEN);
        } else {
            jCheckBox2.setBackground(RED);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox2.setBackground(new java.awt.Color(140, 196, 116));
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setSelected(true);
        jCheckBox2.setText("PBBANK");
        jCheckBox2.setFocusPainted(false);
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });
        jPanel1.add(jCheckBox2);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));
        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        final boolean enabled = evt.getStateChange() == ItemEvent.SELECTED;
        for (AutoDividendRowJPanel autoDividendRowJPanel : autoDividendRowJPanels) {
            autoDividendRowJPanel.setEnabled(enabled);
        }
        updateJCheckBoxColor();
        this.autoDividendJDialog.updateTotalLabel();
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    public void updateTaxInfo(double tax, double taxRate) {
        for (AutoDividendRowJPanel autoDividendRowJPanel : autoDividendRowJPanels) {
            autoDividendRowJPanel.updateTaxInfo(tax, taxRate);
        }
    }
    
    private final List<AutoDividendRowJPanel> autoDividendRowJPanels = new ArrayList<AutoDividendRowJPanel>();
    private final AutoDividendJDialog autoDividendJDialog;
    
    private static final Color GREEN = new java.awt.Color(140, 196, 116);
    private static final Color RED = new java.awt.Color(244, 129, 89);
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
