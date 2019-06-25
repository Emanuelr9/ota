package ar.edu.unahur.obj2;

import ar.edu.unahur.obj2.proveedores.*;
import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;


public class OtaTest {

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

        List<Proveedor> proveedores = Stream.of(sabreAdapter)
                .collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-10-15");


        List<Vuelo> vuelo1 = ota.buscarVuelos(fecha, "Roma", "Paris");


        List<Vuelo> vuelo3 = sabre.buscar(fecha, "Roma","Paris");

        //Compara vuelos.
        Assert.assertEquals(vuelo1, vuelo3);

    }


    @Test
    public void testReservarVuelo() {


        List<Proveedor> proveedores = Stream.of(amadeusAdapter, sabreAdapter)
                .collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");

        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "MIA");

        Vuelo elegido =  vuelos.get(0);

        Set<Pasajero> pasajeros = Stream.of(new Pasajero("Rocky", "Balboa", 0)).collect(Collectors.toSet());

        Boleto boleto = ota.reservar(elegido, pasajeros );

       

        Assert.assertEquals(boleto.getVuelo(), elegido);
    }
}

