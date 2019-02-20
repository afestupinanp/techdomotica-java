/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.awt.event.KeyEvent;

/**
 *
 * @author joanc
 */
public class ConLogin {
    
    public void UsuarioSoloLetras(KeyEvent evt){
       Character s;
        s = evt.getKeyChar();
        if(!Character.isLetter(s) && s != KeyEvent.VK_SPACE){
            evt.consume();
        }
    }

    
}
