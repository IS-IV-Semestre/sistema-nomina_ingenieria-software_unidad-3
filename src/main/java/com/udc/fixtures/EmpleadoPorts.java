package com.udc.fixtures;

import com.udc.application.port.out.*;
import com.udc.domain.exceptions.empleado.EmpleadoNotFound;
import com.udc.domain.models.empleado.Empleado;
import com.udc.domain.models.empleado.EmpleadoAsalariado;
import com.udc.domain.models.empleado.EmpleadoPorComision;
import com.udc.domain.models.empleado.EmpleadoPorHoras;
import com.udc.domain.models.empleado.EmpleadoTemporal;
import com.udc.domain.valueobjects.empleado.EmpleadoId;

import java.util.List;

import static com.udc.fixtures.CustomFixtures.*;

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
            String idStr = id.toString();
            boolean deleted = CustomFixtures.EMPLEADOS_ASALARIADOS.removeIf(e -> e.getId().equals(idStr))
                    || CustomFixtures.EMPLEADOS_POR_HORAS.removeIf(e -> e.getId().equals(idStr))
                    || CustomFixtures.EMPLEADOS_POR_COMISION.removeIf(e -> e.getId().equals(idStr))
                    || CustomFixtures.EMPLEADOS_TEMPORALES.removeIf(e -> e.getId().equals(idStr));

            if (!deleted) throw EmpleadoNotFound.becauseId(idStr);

            EMPLEADOS.removeIf(e -> e.getId().equals(idStr));
            System.out.println("Empleado con ID " + idStr + " ha sido eliminado.");
        }
    };

    public static final GetEmpleadoByIdPort getEmpleadoByIdPort = new GetEmpleadoByIdPort() {
        @Override
        public Empleado execute(EmpleadoId id) {
            return EMPLEADOS.stream().filter(e -> e.getId().equals(id.toString())).findFirst().orElse(null);
        }
    };

    public static final GetAllEmpleadosPort getAllEmpleadosPort = new GetAllEmpleadosPort() {
        @Override
        public List<Empleado> execute() {

            if(!EMPLEADOS.isEmpty()) EMPLEADOS.clear();

            if(!EMPLEADOS_ASALARIADOS.isEmpty()) EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_ASALARIADOS);

            if(!EMPLEADOS_POR_COMISION.isEmpty()) EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_POR_COMISION);

            if(!EMPLEADOS_TEMPORALES.isEmpty()) EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_TEMPORALES);

            if(!EMPLEADOS_POR_HORAS.isEmpty()) EMPLEADOS.addAll(CustomFixtures.EMPLEADOS_POR_HORAS);

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

    public static final SaveEmpleadoPorHorasPort saveEmpleadoPorHorasPort = new SaveEmpleadoPorHorasPort() {
        @Override
        public EmpleadoPorHoras create(EmpleadoPorHoras empleadoPorHoras) {
            CustomFixtures.EMPLEADOS_POR_HORAS.add(empleadoPorHoras);
            EMPLEADOS.add(empleadoPorHoras);
            System.out.println("Empleado por horas con ID " + empleadoPorHoras.getId() + " ha sido creado.");
            return empleadoPorHoras;
        }
    };

    public static final UpdateEmpleadoPorHorasPort updateEmpleadoPorHorasPort = new UpdateEmpleadoPorHorasPort() {
        @Override
        public EmpleadoPorHoras update(EmpleadoPorHoras empleadoPorHoras) {
            int index = EMPLEADOS_POR_HORAS.indexOf(empleadoPorHoras);
            if (index != -1) {
                EMPLEADOS_POR_HORAS.set(index, empleadoPorHoras);
                System.out.println("Empleado por horas con ID " + empleadoPorHoras.getId() + " ha sido actualizado.");
                return empleadoPorHoras;
            }
            throw EmpleadoNotFound.becauseId(empleadoPorHoras.getId());
        }
    };

    public static final SaveEmpleadoPorComisionPort saveEmpleadoPorComisionPort = new SaveEmpleadoPorComisionPort() {
        @Override
        public EmpleadoPorComision create(EmpleadoPorComision empleadoPorComision) {
            CustomFixtures.EMPLEADOS_POR_COMISION.add(empleadoPorComision);
            EMPLEADOS.add(empleadoPorComision);
            System.out.println("Empleado por comisión con ID " + empleadoPorComision.getId() + " ha sido creado.");
            return empleadoPorComision;
        }
    };

    public static final UpdateEmpleadoPorComisionPort updateEmpleadoPorComisionPort = new UpdateEmpleadoPorComisionPort() {
        @Override
        public EmpleadoPorComision update(EmpleadoPorComision empleadoPorComision) {
            int index = EMPLEADOS_POR_COMISION.indexOf(empleadoPorComision);
            if (index != -1) {
                EMPLEADOS_POR_COMISION.set(index, empleadoPorComision);
                System.out.println("Empleado por comisión con ID " + empleadoPorComision.getId() + " ha sido actualizado.");
                return empleadoPorComision;
            }
            throw EmpleadoNotFound.becauseId(empleadoPorComision.getId());
        }
    };

    public static final SaveEmpleadoTemporalPort saveEmpleadoTemporalPort = new SaveEmpleadoTemporalPort() {
        @Override
        public EmpleadoTemporal create(EmpleadoTemporal empleadoTemporal) {
            CustomFixtures.EMPLEADOS_TEMPORALES.add(empleadoTemporal);
            EMPLEADOS.add(empleadoTemporal);
            System.out.println("Empleado temporal con ID " + empleadoTemporal.getId() + " ha sido creado.");
            return empleadoTemporal;
        }
    };

    public static final UpdateEmpleadoTemporalPort updateEmpleadoTemporalPort = new UpdateEmpleadoTemporalPort() {
        @Override
        public EmpleadoTemporal update(EmpleadoTemporal empleadoTemporal) {
            int index = EMPLEADOS_TEMPORALES.indexOf(empleadoTemporal);
            if (index != -1) {
                EMPLEADOS_TEMPORALES.set(index, empleadoTemporal);
                System.out.println("Empleado temporal con ID " + empleadoTemporal.getId() + " ha sido actualizado.");
                return empleadoTemporal;
            }
            throw EmpleadoNotFound.becauseId(empleadoTemporal.getId());
        }
    };

}
