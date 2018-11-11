package com.mms.listener;

import java.util.EventListener;

import com.mms.event.FournisseurEvent;

public interface FournisseurListener extends EventListener {
public void ajouterFournisseur(FournisseurEvent event);
}
