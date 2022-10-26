package com.ciclo3.Sprint3.Controllers;

import com.ciclo3.Sprint3.Models.Empresa;
import com.ciclo3.Sprint3.Service.ServiceInterfaceEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpresaController {

    @Autowired
    private ServiceInterfaceEmpresa EmpresaBDX;

    //Metodos para crear un Enterprise

    @GetMapping("/EnterpriseRegister")
    public String getEnterpriseRegister(Model model){
        model.addAttribute("formEnterpriseRegister",new Empresa());
        return "EnterpriseRegister";
    }

    @PostMapping("/CreateEnterpriseBD")
    public String CreateEmpresa (@ModelAttribute("redirect:/WelcomeEnterprise") Empresa EmpresaX){
        EmpresaBDX.setCreateEmpresa(EmpresaX);
        return "redirect:/WelcomeEnterprise";
    }


    //Matodo para listar Enterprise

    @GetMapping("/EnterpriseList")
    public String getUserList(Model model){
        model.addAttribute("Enterprise",EmpresaBDX.getEmpresa());
        return "EnterpriseList";
    }


    //Metodo para borrar un Enterprise
    @PostMapping("/deleteEnterpriseBD/{idEnterprise}")
    public String deleteEnterprise(@PathVariable Long idEnterprise, Model model){
        try {
            EmpresaBDX.getDeleteEmpresa(idEnterprise);
            return "redirect:/WelcomeEnterprise";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    //Metodo para actualizar un Enterprise

    @GetMapping("/updateEnterpriseBD/{idEnterprise}")
    public String updateEmpresa(@PathVariable Long idEnterprise, Model model){
        try {
            model.addAttribute("EnterpriseUpdate",EmpresaBDX.getOnlyOneEmpresa(idEnterprise));
            return "updateEnterpriseNew";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping("/updateEnterpriseBD")
    public String updateEmpresa (@ModelAttribute("redirect:/WelcomeEnterprise") Empresa EmpresaX){
        try {
            EmpresaBDX.getUpdateEmpresa(EmpresaX);
            return "redirect:/WelcomeEnterprise";
        } catch (Exception e) {
            return "redirect:/error";
        }

    }

    //Metodo para presentar empleados de la empresa
    @GetMapping("/EmployeeOfEnterpriseBD/{idEnterprise}")
    public String EmployeeOfEnterprise(@PathVariable Long idEmpresa, Model model){
        try {
            Empresa EmpresaX = EmpresaBDX.getOnlyOneEmpresa(idEmpresa);
            model.addAttribute("EmployeesEnterprise",EmpresaX.getEmpleados());
            return "EmployeesOfEnterprise";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }
}
