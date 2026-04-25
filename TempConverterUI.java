import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TempConverterUI {

    private static final Color WINDOW_BG = new Color(242, 240, 239);
    private static final Color CARD_TOP = new Color(31, 35, 63);
    private static final Color CARD_BOTTOM = new Color(24, 63, 108);
    private static final Color FIELD_BG = new Color(53, 63, 98);
    private static final Color FIELD_BORDER = new Color(101, 113, 147);
    private static final Color CHIP_BG = new Color(45, 58, 91);
    private static final Color CHIP_BORDER = new Color(91, 107, 142);
    private static final Color RESULT_BG = new Color(52, 71, 111);
    private static final Color RESET_BORDER = new Color(80, 99, 137);
    private static final Color BUTTON_LEFT = new Color(108, 127, 241);
    private static final Color BUTTON_RIGHT = new Color(145, 84, 192);
    private static final Color TEXT_PRIMARY = Color.WHITE;
    private static final Color TEXT_SECONDARY = new Color(198, 207, 228);
    private static final Color TEXT_HINT = new Color(148, 160, 190);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TempConverterUI::createAndShowUI);
    }

    private static void createAndShowUI() {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(WINDOW_BG);
        root.setBorder(new EmptyBorder(12, 12, 12, 12));

        RoundedPanel card = new RoundedPanel(28, CARD_TOP, CARD_BOTTOM);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(350, 560));
        card.setBorder(new EmptyBorder(34, 22, 26, 22));

        ThermometerIcon icon = new ThermometerIcon();
        icon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        card.add(icon);
        card.add(Box.createRigidArea(new Dimension(0, 14)));

        JLabel title = new JLabel("Temperature Converter");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        title.setForeground(TEXT_PRIMARY);
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        card.add(title);

        JLabel subtitle = new JLabel("Celsius - Fahrenheit - Kelvin");
        subtitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        subtitle.setForeground(TEXT_SECONDARY);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        card.add(subtitle);
        card.add(Box.createRigidArea(new Dimension(0, 18)));

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        contentPanel.setMaximumSize(new Dimension(306, Integer.MAX_VALUE));
        contentPanel.setPreferredSize(new Dimension(306, 0));

        JLabel inputLabel = createSectionLabel("Enter Temperature");
        contentPanel.add(inputLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        RoundedTextField inputField = new RoundedTextField("e.g. 25");
        inputField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        inputField.setPreferredSize(new Dimension(306, 34));
        contentPanel.add(inputField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 14)));

        JLabel unitLabel = createSectionLabel("Select Unit");
        contentPanel.add(unitLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel unitPanel = new JPanel(new GridLayout(1, 3, 8, 0));
        unitPanel.setOpaque(false);
        unitPanel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        unitPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));

        UnitToggleButton celsiusButton = new UnitToggleButton("Celsius", "°C Celsius");
        UnitToggleButton fahrenheitButton = new UnitToggleButton("Fahrenheit", "°F Fahrenheit");
        UnitToggleButton kelvinButton = new UnitToggleButton("Kelvin", "K Kelvin");

        ButtonGroup group = new ButtonGroup();
        group.add(celsiusButton);
        group.add(fahrenheitButton);
        group.add(kelvinButton);
        celsiusButton.setSelected(true);

        unitPanel.add(celsiusButton);
        unitPanel.add(fahrenheitButton);
        unitPanel.add(kelvinButton);
        contentPanel.add(unitPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 14)));

        GradientButton convertButton = new GradientButton("Convert Temperature");
        convertButton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        convertButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        convertButton.setPreferredSize(new Dimension(306, 38));
        contentPanel.add(convertButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        JPanel resultsContainer = new JPanel();
        resultsContainer.setOpaque(false);
        resultsContainer.setLayout(new BoxLayout(resultsContainer, BoxLayout.Y_AXIS));
        resultsContainer.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        resultsContainer.setVisible(false);

        ResultCard celsiusCard = new ResultCard("Celsius", new Color(85, 154, 250), "\u00B0C");
        ResultCard fahrenheitCard = new ResultCard("Fahrenheit", new Color(255, 84, 108), "\u00B0F");
        ResultCard kelvinCard = new ResultCard("Kelvin", new Color(165, 107, 234), "K");

        resultsContainer.add(celsiusCard);
        resultsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        resultsContainer.add(fahrenheitCard);
        resultsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        resultsContainer.add(kelvinCard);
        resultsContainer.add(Box.createRigidArea(new Dimension(0, 12)));

        OutlineButton resetButton = new OutlineButton("Reset");
        resetButton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        resetButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        resetButton.setPreferredSize(new Dimension(306, 32));
        resultsContainer.add(resetButton);

        contentPanel.add(resultsContainer);
        card.add(contentPanel);
        card.add(Box.createVerticalGlue());

        convertButton.addActionListener(e -> {
            try {
                double temp = Double.parseDouble(inputField.getText().trim());
                String selectedUnit = getSelectedUnit(celsiusButton, fahrenheitButton, kelvinButton);

                double celsius;
                double fahrenheit;
                double kelvin;

                if ("Celsius".equals(selectedUnit)) {
                    celsius = temp;
                    fahrenheit = (temp * 9 / 5) + 32;
                    kelvin = temp + 273.15;
                } else if ("Fahrenheit".equals(selectedUnit)) {
                    celsius = (temp - 32) * 5 / 9;
                    fahrenheit = temp;
                    kelvin = celsius + 273.15;
                } else {
                    kelvin = temp;
                    celsius = temp - 273.15;
                    fahrenheit = (celsius * 9 / 5) + 32;
                }

                celsiusCard.setValue(celsius);
                fahrenheitCard.setValue(fahrenheit);
                kelvinCard.setValue(kelvin);
                resultsContainer.setVisible(true);
                frame.pack();
            } catch (NumberFormatException ex) {
                UIManager.put("OptionPane.background", new Color(31, 35, 63));
                UIManager.put("Panel.background", new Color(31, 35, 63));
                UIManager.put("OptionPane.messageForeground", Color.WHITE);
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        resetButton.addActionListener(e -> {
            inputField.setText("");
            celsiusButton.setSelected(true);
            celsiusCard.clearValue();
            fahrenheitCard.clearValue();
            kelvinCard.clearValue();
            resultsContainer.setVisible(false);
            inputField.requestFocusInWindow();
            frame.pack();
        });

        root.add(card, new GridBagConstraints());
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        label.setForeground(TEXT_PRIMARY);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        return label;
    }

    private static String getSelectedUnit(UnitToggleButton celsiusButton,
                                          UnitToggleButton fahrenheitButton,
                                          UnitToggleButton kelvinButton) {
        if (celsiusButton.isSelected()) {
            return celsiusButton.getUnitKey();
        }
        if (fahrenheitButton.isSelected()) {
            return fahrenheitButton.getUnitKey();
        }
        return kelvinButton.getUnitKey();
    }

    private static class RoundedPanel extends JPanel {
        private final int arc;
        private final Color startColor;
        private final Color endColor;

        RoundedPanel(int arc, Color startColor, Color endColor) {
            this.arc = arc;
            this.startColor = startColor;
            this.endColor = endColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(0, 0, startColor, 0, getHeight(), endColor));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class RoundedTextField extends JTextField {
        private final String placeholder;

        RoundedTextField(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
            setForeground(TEXT_PRIMARY);
            setCaretColor(TEXT_PRIMARY);
            setFont(new Font("Segoe UI", Font.PLAIN, 12));
            setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(FIELD_BG);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            g2.dispose();

            super.paintComponent(g);

            if (getText().isEmpty() && !isFocusOwner()) {
                Graphics2D g3 = (Graphics2D) g.create();
                g3.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g3.setColor(TEXT_HINT);
                g3.setFont(getFont());
                g3.drawString(placeholder, 14, 22);
                g3.dispose();
            }
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(isFocusOwner() ? new Color(146, 127, 233) : FIELD_BORDER);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
            g2.dispose();
        }
    }

    private static class GradientButton extends JButton {
        GradientButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(TEXT_PRIMARY);
            setFont(new Font("Segoe UI", Font.BOLD, 11));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(0, 0, BUTTON_LEFT, getWidth(), 0, BUTTON_RIGHT));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class OutlineButton extends JButton {
        OutlineButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(TEXT_SECONDARY);
            setFont(new Font("Segoe UI", Font.BOLD, 10));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 0));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            g2.setColor(RESET_BORDER);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class UnitToggleButton extends JToggleButton {
        private final String unitKey;

        UnitToggleButton(String unitKey, String text) {
            super(text);
            this.unitKey = unitKey;
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(TEXT_SECONDARY);
            setFont(new Font("Segoe UI", Font.BOLD, 10));
            setHorizontalAlignment(SwingConstants.CENTER);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        String getUnitKey() {
            return unitKey;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (isSelected()) {
                g2.setPaint(new GradientPaint(0, 0, BUTTON_LEFT, getWidth(), 0, BUTTON_RIGHT));
                setForeground(TEXT_PRIMARY);
            } else {
                g2.setColor(CHIP_BG);
                setForeground(TEXT_SECONDARY);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            g2.setColor(CHIP_BORDER);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class ResultCard extends JPanel {
        private final JLabel valueLabel;
        private final String suffix;
        private final Color dotColor;

        ResultCard(String title, Color dotColor, String suffix) {
            this.suffix = suffix;
            this.dotColor = dotColor;
            setOpaque(false);
            setLayout(new GridBagLayout());
            setBorder(new EmptyBorder(12, 14, 12, 14));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));
            setPreferredSize(new Dimension(306, 56));
            setAlignmentX(JComponent.LEFT_ALIGNMENT);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel titleLabel = new JLabel(title);
            titleLabel.setForeground(TEXT_SECONDARY);
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
            add(titleLabel, gbc);

            valueLabel = new JLabel("--");
            valueLabel.setForeground(TEXT_PRIMARY);
            valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
            gbc.gridy = 1;
            gbc.insets = new java.awt.Insets(4, 0, 0, 0);
            add(valueLabel, gbc);
        }

        void setValue(double value) {
            valueLabel.setText(String.format("%.2f %s", value, suffix));
        }

        void clearValue() {
            valueLabel.setText("--");
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(RESULT_BG);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
            g2.setColor(new Color(92, 110, 147));
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);

            GradientPaint dotPaint = new GradientPaint(
                    getWidth() - 36, getHeight() / 2f - 8, brighter(dotColor),
                    getWidth() - 20, getHeight() / 2f + 8, dotColor);
            g2.setPaint(dotPaint);
            g2.fillOval(getWidth() - 34, (getHeight() - 18) / 2, 18, 18);
            g2.dispose();
            super.paintComponent(g);
        }

        private Color brighter(Color color) {
            return new Color(
                    Math.min(color.getRed() + 35, 255),
                    Math.min(color.getGreen() + 35, 255),
                    Math.min(color.getBlue() + 35, 255)
            );
        }
    }

    private static class ThermometerIcon extends JComponent {
        ThermometerIcon() {
            setPreferredSize(new Dimension(18, 28));
            setMaximumSize(new Dimension(18, 28));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(9, 11, 20));
            g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawRoundRect(7, 2, 4, 15, 4, 4);
            g2.fillOval(4, 15, 10, 10);
            g2.drawLine(9, 6, 9, 17);
            g2.dispose();
        }
    }
}
