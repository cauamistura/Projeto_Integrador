package model.interfaces;

import model.MTDadosUser;

public interface InterfaceConsUser {
	
	public void preencheUserCad(MTDadosUser listUser);

	public void desabilitaBotoes(boolean b);

	public void habilitaBotoes(boolean b);

	public void exluiUser(Integer bdiduser);
	
}
