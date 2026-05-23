package com.udc.infrastructure.persistence.inmemory;

import com.udc.application.port.out.*;
import com.udc.domain.exceptions.empleado.EmpleadoNotFound;
import com.udc.domain.models.empleado.*;
import com.udc.domain.valueobjects.empleado.EmpleadoId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpleadoRepositoryInMemory implements
        SaveEmpleadoAsalariadoPort,
        UpdateEmpleadoAsalariadoPort,
        SaveEmpleadoPorHorasPort,
        UpdateEmpleadoPorHorasPort,
        SaveEmpleadoPorComisionPort,
        UpdateEmpleadoPorComisionPort,
        SaveEmpleadoTemporalPort,
        UpdateEmpleadoTemporalPort,
        GetEmpleadoByIdPort,
        GetAllEmpleadosPort,
        DeleteEmpleadoPort {

    private final Map<String, Empleado> store = new HashMap<>();

    // ── Save ────────────────────────────────────────────────────────────────

    @Override
    public EmpleadoAsalariado create(EmpleadoAsalariado empleado) {
        store.put(empleado.getId(), empleado);
        return empleado;
    }

    @Override
    public EmpleadoPorHoras create(EmpleadoPorHoras empleado) {
        store.put(empleado.getId(), empleado);
        return empleado;
    }

    @Override
    public EmpleadoPorComision create(EmpleadoPorComision empleado) {
        store.put(empleado.getId(), empleado);
        return empleado;
    }

    @Override
    public EmpleadoTemporal create(EmpleadoTemporal empleado) {
        store.put(empleado.getId(), empleado);
        return empleado;
    }

    // ── Update ──────────────────────────────────────────────────────────────

    @Override
    public EmpleadoAsalariado update(EmpleadoAsalariado empleado) {
        requireExists(empleado.getId());
        store.put(empleado.getId(), empleado);
        return empleado;
    }

    @Override
    public EmpleadoPorHoras update(EmpleadoPorHoras empleado) {
        requireExists(empleado.getId());
        store.put(empleado.getId(), empleado);
        return empleado;
    }

    @Override
    public EmpleadoPorComision update(EmpleadoPorComision empleado) {
        requireExists(empleado.getId());
        store.put(empleado.getId(), empleado);
        return empleado;
    }

    @Override
    public EmpleadoTemporal update(EmpleadoTemporal empleado) {
        requireExists(empleado.getId());
        store.put(empleado.getId(), empleado);
        return empleado;
    }

    // ── Query ───────────────────────────────────────────────────────────────

    @Override
    public Empleado execute(EmpleadoId id) {
        Empleado empleado = store.get(id.toString());
        if (empleado == null) throw EmpleadoNotFound.becauseId(id.toString());
        return empleado;
    }

    @Override
    public List<Empleado> execute() {
        return new ArrayList<>(store.values());
    }

    // ── Delete ──────────────────────────────────────────────────────────────

    @Override
    public void delete(EmpleadoId id) {
        requireExists(id.toString());
        store.remove(id.toString());
    }

    // ── Helper ──────────────────────────────────────────────────────────────

    private void requireExists(String id) {
        if (!store.containsKey(id)) throw EmpleadoNotFound.becauseId(id);
    }
}
