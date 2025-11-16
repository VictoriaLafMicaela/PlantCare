package com.example.plantcare;

import java.util.ArrayList;
import java.util.List;

public class PlantLista {

    private static List<Planta> plantas = new ArrayList<>();

    public static void agregarPlanta(Planta p) {
        plantas.add(p);
    }

    public static List<Planta> obtenerPlantas() {
        return plantas;
    }
}
