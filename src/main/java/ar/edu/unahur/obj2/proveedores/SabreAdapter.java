package ar.edu.unahur.obj2.proveedores;

import ar.edu.unahur.obj2.Boleto;
import ar.edu.unahur.obj2.Pasajero;
import ar.edu.unahur.obj2.Vuelo;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;

public class SabreAdapter implements Proveedor{

    Sabre sabre;

    public SabreAdapter(Sabre sabre) {
        this.sabre = sabre;
    }

    @Override
    public List<Vuelo> buscarVuelo(DateTime fecha, String origen, String destino) {
        return sabre.buscar(fecha,origen,destino);
    }

    @Override
    public Boleto comprar(Vuelo vuelo, Set<Pasajero> pasajeros) {
        return sabre.comprar(vuelo,pasajeros);
    }
}
