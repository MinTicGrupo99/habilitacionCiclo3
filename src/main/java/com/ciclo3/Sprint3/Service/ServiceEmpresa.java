package com.ciclo3.Sprint3.Service;

import com.ciclo3.Sprint3.Models.Empresa;
import com.ciclo3.Sprint3.Repositories.RepositoryEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmpresa implements  ServiceInterfaceEmpresa {


    //Atribute
    Date Today = new Date();

    //Inyectar un objeto del repositorio de la entidad Empresa
    @Autowired
    RepositoryEmpresa repositoryEmpresa;

    @Override
    public List<Empresa> getEmpresa() {
        return repositoryEmpresa.findAll();
    }

    @Override
    public Empresa getOnlyOneEmpresa(Long idEmpresa) throws Exception {
        Optional<Empresa> EmpresaBD = repositoryEmpresa.findById(idEmpresa);
        if(EmpresaBD.isPresent()){
            return EmpresaBD.get();
        }
        throw new Exception("IdEmpresa no asignado a ninguna Empresa de nuestra base de datos");
    }

    @Override
    public String setCreateEmpresa(Empresa EmpresaIn) {
        //Preguntamos si ya hay alguna Empresa ya registrada con ese Id.
        Optional<Empresa> EmpresaBD = repositoryEmpresa.findById(EmpresaIn.getIdEmpresa());
        if(!EmpresaBD.isPresent()){
            repositoryEmpresa.save(EmpresaIn);
            return "Empresa Creada con exito";

        }
        return ("Ese Id ya esta regitrado a una Empresa Existente");
    }

    @Override
    public String getCreateEmpresa(Empresa empresaIn) {
        return null;
    }

    @Override
    public Empresa getUpdateEmpresa(Empresa empresaIn) throws Exception {

        Empresa EmpresaBD = getOnlyOneEmpresa(empresaIn.getIdEmpresa());
        if (empresaIn.getNombreEmpresa() != null && !empresaIn.getNombreEmpresa().equals("")) {
            EmpresaBD.setNombreEmpresa(empresaIn.getNombreEmpresa());
        }

        if (empresaIn.getNitEmpresa() != null && !empresaIn.getNitEmpresa().equals("")) {
            EmpresaBD.setNitEmpresa(empresaIn.getNitEmpresa());
        }

        if (empresaIn.getDireccionEmpresa() != null && !empresaIn.getDireccionEmpresa().equals("")) {
            EmpresaBD.setDireccionEmpresa(empresaIn.getDireccionEmpresa());
        }

        if (empresaIn.getTelefonoEmpresa() != null && !empresaIn.getTelefonoEmpresa().equals("")) {
            EmpresaBD.setTelefonoEmpresa(empresaIn.getTelefonoEmpresa());
        }
        if(empresaIn.getCreatedAtEmpresa()!=null && !empresaIn.getCreatedAtEmpresa().equals("")){
            EmpresaBD.setCreatedAtEmpresa(empresaIn.getCreatedAtEmpresa());
        }

        EmpresaBD.setUpdatedAtEmpresa(Today);

        return repositoryEmpresa.save(EmpresaBD);
    }

    @Override
    public String getDeleteEmpresa(Long idEmpresa) throws Exception {
        Optional<Empresa> EmpresaBD = repositoryEmpresa.findById(idEmpresa);
        if(EmpresaBD.isPresent()){
            repositoryEmpresa.deleteById(idEmpresa);
            return "Empresa Eliminada con exito";
        }
        throw new Exception("Empresa NOOOOO encontarda");
    }


}

