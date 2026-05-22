package com.udc.fixtures;

import com.udc.application.port.out.*;
import com.udc.domain.exceptions.empleado.EmpleadoNotFound;
import com.udc.domain.models.empleado.Empleado;
import com.udc.domain.models.empleado.EmpleadoAsalariado;
import com.udc.domain.valueobjects.empleado.EmpleadoId;

import java.util.List;

import static com.udc.fixtures.CustomFixtures.EMPLEADOS;
import static com.udc.fixtures.CustomFixtures.EMPLEADOS_ASALARIADOS;

public class EmpleadoPorts {
    public static final SaveEmpleadoAsalariadoPort saveEmpleadoAsalariadoPort = new SaveEmpleadoAsalariadoPort() {
        @Override
        public EmpleadoAsalariado create(EmpleadoAsalariado empleadoAsalariado) {
            CustomFixtures.EMPLEADOS_ASALARIADOS.add(empleadoAsalariado);
            System.out.println("Empleado con ID " + empleadoAsalariado.getId() + " ha sido creado.");
            EMPLEADOS.add(empleadoAsalariado);
            return empleadoAsalariado;
        }
    };

    public static final DeleteEmpleadoPort deleteEmpleadoPort = new DeleteEmpleadoPort() {
        @Override
        public void delete(EmpleadoId id) {
            boolean deleted = CustomFixtures.EMPLEADOS_ASALARIADOS.removeIf(empleado -> empleado.getId().equals(id));

            if (!deleted) throw EmpleadoNotFound.becauseId(id.toString());

            if(EMPLEADOS != null || !EMPLEADOS.isEmpty()){
                EMPLEADOS.removeIf(empleado -> {
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
            return (Empleado) EMPLEADOS.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        }
    };

    public static final GetAllEmpleadosPort getAllEmpleadosPort = new GetAllEmpleadosPort() {
        @Override
        public List<Empleado> execute() {
            EMPLEADOS.clear();
            EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_ASALARIADOS);
            EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_POR_COMISION);
            EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_TEMPORALES);
            EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_POR_HORAS);

            return EMPLEADOS;
        }
    };

    public static final UpdateEmpleadoAsalariadoPort updateEmpleadoAsalariadoPort = new UpdateEmpleadoAsalariadoPort() {
        @Override
        public EmpleadoAsalariado update(EmpleadoAsalariado empleadoAsalariado) {
            int index = EMPLEADOS_ASALARIADOS.indexOf(empleadoAsalariado);
            if (index != -1) {
                EMPLEADOS_ASALARIADOS.set(index, empleadoAsalariado);
                System.out.println("Empleado con ID " + empleadoAsalariado.getId() + " ha sido actualizado.");
                return empleadoAsalariado;
            }
            throw EmpleadoNotFound.becauseId(empleadoAsalariado.getId());
        }
    };

}
