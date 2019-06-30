package ar.edu.unahur.obj2;

import ar.edu.unahur.obj2.proveedores.*;
import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestWorldspan {

    Amadeus amadeus;
    Sabre sabre;
    Worldspan worldspan;

    AmadeusAdapter amadeusAdapter;
    SabreAdapter sabreAdapter;
    WorldspanAdapter worldspanAdapter;

    @BeforeTest
    public void beforeTest(){
        amadeus = new Amadeus();
        sabre = new Sabre();
        worldspan = new Worldspan();

        amadeusAdapter = new AmadeusAdapter(amadeus);
        sabreAdapter = new SabreAdapter(sabre);
        worldspanAdapter = new WorldspanAdapter(worldspan);
    }
    @Test

    public void TestBuscarVuelos(){

        List<Proveedor> proveedores = Stream.of(worldspanAdapter)
                .collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");


        List<Vuelo> vuelo1 = ota.buscarVuelos(fecha, "BUE", "NYC");


        List<Vuelo> vuelo3 = sabre.buscar(fecha, "BUE","NYC");

        //Compara vuelos.
        Assert.assertEquals(vuelo1, vuelo3);

    }

    @Test
    public void testReservarVuelo() {


        List<Proveedor> proveedores = Stream.of(worldspanAdapter)
                .collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");

        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "NYC");

        Vuelo elegido =  vuelos.get(0);

        Set<Pasajero> pasajeros = Stream.of(new Pasajero("Liam", "Gallagher", 0)).collect(Collectors.toSet());

        Boleto boleto = ota.reservar(elegido, pasajeros );



        Assert.assertEquals(boleto.getVuelo(), elegido);
    }

}

