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

        // Cria os botões personalizados
        okButton = new RoundButton("OK");
        okButton.setBounds(76, 156, 73, 21);
        cancelButton = new RoundButton("Cancelar");
        cancelButton.setBounds(189, 156, 87, 21);


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
        
  
        if(!mensagem) {
        	cancelButton.setVisible(mensagem);
        	okButton.setBounds(145, 156, 73, 21);
        }
        


        // Adiciona os botões ao diálogo
        getContentPane().add(okButton);
        getContentPane().add(cancelButton);
        
        JLabel lblConteudo = new JLabel(conteudo);
        lblConteudo.setBounds(153, 63, 145, 13);
        getContentPane().add(lblConteudo);
        
        JLabel lblIcon = new JLabel("");
        lblIcon.setIcon(new ImageIcon("C:\\Git\\Projeto_Integrador\\ClinicaPets\\src\\main\\resources\\dogmal.png"));
        lblIcon.setBounds(21, 10, 100, 118);
        getContentPane().add(lblIcon);

        // Configura o tamanho do diálogo
        setSize(354, 245);
        setLocationRelativeTo(parent);
    }

    public boolean showDialog() {
        setVisible(true); // Torna o diálogo visível
        return dialogResult; // Retorna o resultado do diálogo
    }
}