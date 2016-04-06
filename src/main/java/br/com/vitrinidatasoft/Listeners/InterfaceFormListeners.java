/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import java.awt.event.ActionListener;

/**
 *
 * @author mrhell
 */
public interface InterfaceFormListeners extends ActionListener{
    public void attachListener();
    public void resetFields();
    public void turnButtonsOn();
    public void turnButtonsOf();
    public void cancelAction();
    public void disableEditTexts();
    public void enableEditTexts();   
    public void actionPerformedNovo();
    public void actionPerformedSalvar();
    public void actionPerfomedEditar();
    public void actionPerformedCancelar();
}
