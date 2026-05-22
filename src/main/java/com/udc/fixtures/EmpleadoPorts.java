package com.udc.fixtures;

import com.udc.application.port.out.DeleteEmpleadoPort;
import com.udc.application.port.out.GetAllEmpleadosPort;
import com.udc.application.port.out.GetEmpleadoByIdPort;
import com.udc.application.port.out.SaveEmpleadoAsalariadoPort;
import com.udc.domain.exceptions.empleado.EmpleadoNotFound;
import com.udc.domain.exceptions.empleado.InvalidEmpleadoId;
import com.udc.domain.models.empleado.Empleado;
import com.udc.domain.models.empleado.EmpleadoAsalariado;
import com.udc.domain.valueobjects.empleado.EmpleadoId;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmpleadoPorts {
    public static final SaveEmpleadoAsalariadoPort saveEmpleadoAsalariadoPort = new SaveEmpleadoAsalariadoPort() {
        @Override
        public EmpleadoAsalariado create(EmpleadoAsalariado empleadoAsalariado) {
            CustomFixtures.EMPLEADOS_ASALARIADOS.add(empleadoAsalariado);
            System.out.println("Empleado con ID " + empleadoAsalariado.getId() + " ha sido creado.");
            CustomFixtures.EMPLEADOS.add(empleadoAsalariado);
            return empleadoAsalariado;
        }
    };

    public static final DeleteEmpleadoPort deleteEmpleadoPort = new DeleteEmpleadoPort() {
        @Override
        public void delete(EmpleadoId id) {
            boolean deleted = CustomFixtures.EMPLEADOS_ASALARIADOS.removeIf(empleado -> empleado.getId().equals(id));

            if (!deleted) throw EmpleadoNotFound.becauseId(id.toString());

            if(CustomFixtures.EMPLEADOS != null || !CustomFixtures.EMPLEADOS.isEmpty()){
                CustomFixtures.EMPLEADOS.removeIf(empleado -> {
                    if(empleado instanceof EmpleadoAsalariado){
                        return ((EmpleadoAsalariado) empleado).getId().equals(id);
                    }
                    return false;
                });
            }

            System.out.println("Empleado con ID " + id + " ha sido eliminado.");
        }
    };

    public static final GetEmpleadoByIdPort getEmpleadoByIdPort = new GetEmpleadoByIdPort() {
        @Override
        public Empleado execute(EmpleadoId id) {
            return (Empleado) CustomFixtures.EMPLEADOS.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        }
    };

    public static final GetAllEmpleadosPort getAllEmpleadosPort = new GetAllEmpleadosPort() {
        @Override
        public Optional<List<Empleado>> execute() {
            CustomFixtures.EMPLEADOS.clear();
            CustomFixtures.EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_ASALARIADOS);
            CustomFixtures.EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_POR_COMISION);
            CustomFixtures.EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_TEMPORALES);
            CustomFixtures.EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_POR_HORAS);

            return Optional.of(CustomFixtures.EMPLEADOS);
        }
    };

}
