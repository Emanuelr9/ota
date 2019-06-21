package ar.edu.unahur.obj2;
import ar.edu.unahur.obj2.proveedores.Proveedor;

import java.util.List;
import java.util.Random;

public class DistribuidorDeTrafico {

    public DistribuidorDeTrafico(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }


    private Random random = new Random();
    private List<Proveedor>proveedores;


    public void addProveedor(Proveedor proveedor){
        proveedores.add(proveedor);
    }

    public Proveedor proveedor() {

        return proveedores.get(random.nextInt(proveedores.size()));

    }







    }

