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

public class TestAmadeus {

    Amadeus amadeus;
    Sabre sabre;
    Worldspan worldspan;

    AmadeusAdapter amadeusAdapter;





    @BeforeTest
    public void beforeTest(){
        amadeus = new Amadeus();
        sabre = new Sabre();
        worldspan = new Worldspan();

        amadeusAdapter = new AmadeusAdapter(amadeus);

    }

    @Test

    public void TestBuscarVuelos(){

        List<Proveedor> proveedores = Stream.of(amadeusAdapter)
                .collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-15");


        List<Vuelo> vuelo1 = ota.buscarVuelos(fecha, "BUE", "SAO");


        List<Vuelo> vuelo3 = sabre.buscar(fecha, "BUE","SAO");

        //Compara vuelos.
        Assert.assertEquals(vuelo1, vuelo3);

    }
    @Test
    public void testReservarVuelo() {


        List<Proveedor> proveedores = Stream.of(amadeusAdapter)
                .collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-15");

        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "SAO");

        Vuelo elegido =  vuelos.get(0);

        Set<Pasajero> pasajeros = Stream.of(new Pasajero("Bono", "Box", 0)).collect(Collectors.toSet());

        Boleto boleto = ota.reservar(elegido, pasajeros );



        Assert.assertEquals(boleto.getVuelo(), elegido);
    }





}
