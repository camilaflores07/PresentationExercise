/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

/**
 *
 * @author 50494
 */

class Boleto {
    private String tipo;
    private String codigo;

    public Boleto(String tipo, String codigo) {
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Boleto)) return false;
        Boleto otro = (Boleto) obj;
        return this.codigo.equals(otro.codigo);
    }
}


