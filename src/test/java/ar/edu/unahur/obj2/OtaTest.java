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

import static org.testng.Assert.assertEquals;

public class OtaTest {



        public Amadeus amadeus;
        public Sabre sabre;
        public Worldspan worldspan;

        public Ota ota;
        public Proveedor proveedor;
        public DistribuidorDeTrafico distribuidorDeTrafico;

        public AmadeusAdapter amadeusAdapter;
        public SabreAdapter sabreAdapter;
        public WorldspanAdapter worldspanAdapter;


        public List<Proveedor> proveedores;
        public List<Proveedor> proveedorSabre;
        public List<Proveedor> proveedorAmadeus;
        public List<Proveedor> proveedorWorldspan;
        @BeforeTest

        public void setup_Ota() {

            Amadeus amadeus = new Amadeus();
            Sabre sabre = new Sabre();
            Worldspan worldspan = new Worldspan();


            sabreAdapter = new SabreAdapter(sabre);
            amadeusAdapter = new AmadeusAdapter(amadeus);
            worldspanAdapter = new WorldspanAdapter(worldspan);

            List<Proveedor> proveedores;
            List<Proveedor> proveedorSabre;
            List<Proveedor> proveedorAmadeus;
            List<Proveedor> proveedorWorldspan;

            proveedores = Stream.of(sabreAdapter, worldspanAdapter, amadeusAdapter).collect(Collectors.toList());
            proveedorSabre = Stream.of(sabreAdapter).collect(Collectors.toList());
            proveedorAmadeus = Stream.of(amadeusAdapter).collect(Collectors.toList());
            proveedorWorldspan = Stream.of(worldspanAdapter).collect(Collectors.toList());


        }

        @Test
        public void testVueloAmadeus() {
            DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedorAmadeus);
            Ota ota = new Ota(distribuidorDeTrafico);

            DateTime fecha = new DateTime("2020-01-15");

            List<Vuelo> vuelosOta = ota.buscarVuelos(fecha, "Roma", "Paris");
            List<Vuelo> vueloAmadeus = amadeus.executeSearch(fecha, "Roma", "Paris");

            assertEquals(vuelosOta, vueloAmadeus);

        }
      @Test
       public void testVueloWorldspan() {
        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedorWorldspan);
        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");
        List<Vuelo> vuelosOta = ota.buscarVuelos(fecha, "Toronto", "Orlando");
        List<Vuelo> vueloWorldspan = worldspan.searchFlights(13,12,2019, "Toronto", "Orlando");

        Assert.assertEquals(vuelosOta, vueloWorldspan);

    }

    @Test
    public void testVueloSabrespan() {
        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedorSabre);
        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-09-25");
        List<Vuelo> vuelosOta = ota.buscarVuelos(fecha, "Milan", "Londres");
        List<Vuelo> vueloWorldspan = worldspan.searchFlights(13,12,2019, "Milan", "Londres");

        Assert.assertEquals(vuelosOta, vueloWorldspan);

    }






    @org.testng.annotations.Test
    public void testBuscarVuelos() {
        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedorAmadeus);
        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");


        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "MIA");


    }

    @org.testng.annotations.Test
    public void testReservar() {
        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedorSabre);
        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");


        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "MIA");

        Vuelo elegido = vuelos.get(0);
        Set<Pasajero> pasajeros = Stream.of(new Pasajero("Juan", "Pérez", 40)).collect(Collectors.toSet());

        Boleto boleto = ota.reservar(elegido, pasajeros);

        assertEquals(boleto.getVuelo(), elegido);


    }




}

