package br.ufmg.rt.transito;

public class Via {
	
	
	 private Radar origem;
	 private Radar destino;
	 private String nomeDaVia;
	 
	 private boolean status; // true => livre; false => interditada

	 public Via(Radar radarOrigem, Radar radarDestino) {
	        this.origem = radarOrigem;
	        this.destino = radarDestino;
	        this.nomeDaVia = this.getNomeDaVia();
	        this.status = true;
	 }
	
	 public Radar getOrigem() {
		return origem;
	}

	public void setOrigem(Radar origem) {
		this.origem = origem;
	}

	public Radar getDestino() {
		return destino;
	}

	public void setDestino(Radar destino) {
		this.destino = destino;
	}

	public void setNomeDaVia(String nomeDaVia) {
		this.nomeDaVia = nomeDaVia;
	}
	
	public String getNomeDaVia(){
		String name = origem.getName() + "_" + destino.getName();
		return name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
