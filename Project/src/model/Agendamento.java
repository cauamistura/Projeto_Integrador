package model;

import java.time.LocalDate;

import control.ObjectDAO;

public class Agendamento extends Pet{
	
	private Integer id;
	private LocalDate dateAgendamento;
	private Boolean disponivel;
	private String hora;
	
	public Boolean getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
	public Integer getId() {
		return id;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDateAgendamento() {
		return dateAgendamento;
	}
	public void setDateAgendamento(LocalDate dateAgendamento) {
		this.dateAgendamento = dateAgendamento;
	}
	
}
