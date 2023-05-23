package model;

import java.time.LocalDate;

import control.ObjectDAO;

public class Agendamento extends ObjectDAO{
	
	private Integer id, hora;
	private LocalDate dateAgendamento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHora() {
		return hora;
	}
	public void setHora(Integer hora) {
		this.hora = hora;
	}
	public LocalDate getDateAgendamento() {
		return dateAgendamento;
	}
	public void setDateAgendamento(LocalDate dateAgendamento) {
		this.dateAgendamento = dateAgendamento;
	}
	
}
