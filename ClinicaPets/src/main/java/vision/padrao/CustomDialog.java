package vision.padrao;
import javax.swing.*;

import vision.padrao.RoundButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog {
    private JButton okButton;
    private JButton cancelButton;
    private boolean dialogResult; // Variável para armazenar o resultado do diálogo

    public CustomDialog(String title, String conteudo, Frame parent, boolean modal, Boolean mensagem ) {
        super(parent, title, modal);
        getContentPane().setBackground(new Color(158, 174, 255));

        // Cria os botões personalizados
        okButton = new RoundButton("OK");
        okButton.setBounds(184, 112, 73, 21);
        cancelButton = new RoundButton("Cancelar");
        cancelButton.setBounds(349, 112, 87, 21);
       

        // Adiciona a ação ao botão OK
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica do botão OK
                dialogResult = true; // Define o resultado do diálogo como true
                dispose(); // Fecha o diálogo
            }
        });

        // Adiciona a ação ao botão Cancelar
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica do botão Cancelar
                dialogResult = false; // Define o resultado do diálogo como false
                dispose(); // Fecha o diálogo
            }
        });
        getContentPane().setLayout(null);
        
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(Util.getCaminhoIMG("atencao.png")));
        lblIcon.setBounds(10, 0, 113, 156);
        getContentPane().add(lblIcon);

        
        if(!mensagem) {
        	cancelButton.setVisible(mensagem);
        	lblIcon.setIcon(new ImageIcon(Util.getCaminhoIMG("GatoConfirma.png")));
        	okButton.setBounds(276, 112, 73, 21);
        }
        
        // Adiciona os botões ao diálogo
        getContentPane().add(okButton);
        getContentPane().add(cancelButton);
        
        JLabel lblConteudo = new JLabel(conteudo);
        lblConteudo.setHorizontalAlignment(SwingConstants.CENTER);
        lblConteudo.setBounds(118, 37, 401, 46);
        getContentPane().add(lblConteudo);
        
        
        // Configura o tamanho do diálogo
        setSize(543, 198);
        setLocationRelativeTo(parent);
    }

    public boolean showDialog() {
        setVisible(true); // Torna o diálogo visível
        return dialogResult; // Retorna o resultado do diálogo
    }
}