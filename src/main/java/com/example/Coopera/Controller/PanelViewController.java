package com.example.Coopera.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Coopera.Repository.SocioRepository;
import com.example.Coopera.Repository.PagoMensualCreditoRepository;
import com.example.Coopera.Repository.ProductoInversionSocioRepository;
import com.example.Coopera.Repository.UsuarioClaveRepository;
import com.example.Coopera.Repository.MovimientoRepository;
import com.example.Coopera.Repository.CuotaCreditoSocioRepository;

@Controller
public class PanelViewController {
    
    @Autowired
    private SocioRepository socioRepository;
    
    @Autowired
    private PagoMensualCreditoRepository pagoMensualCreditoRepository;
    
    @Autowired
    private ProductoInversionSocioRepository productoInversionSocioRepository;
    
    @Autowired
    private UsuarioClaveRepository usuarioClaveRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuotaCreditoSocioRepository cuotaCreditoSocioRepository;
    
    @GetMapping("/")
    public String home() {
        return "panel-menu";
    }
    
    @GetMapping("/panel/test")
    public String panelTest() {
        return "panel-test";
    }
    
    @GetMapping("/panel/socios")
    public String panelSocios(Model model) {
        // La vista hará fetch a /api/v2/socios; evitamos cargar datos pesados aquí
        return "panel-socios";
    }
    
    @GetMapping("/panel/pagos")
    public String panelPagos(Model model) {
        model.addAttribute("pagos", pagoMensualCreditoRepository.findAll());
        return "panel-pagos";
    }
    
    @GetMapping("/panel/inversiones")
    public String panelInversiones(Model model) {
        model.addAttribute("inversiones", productoInversionSocioRepository.findAll());
        return "panel-inversiones";
    }
    
    @GetMapping("/panel/usuarios")
    public String panelUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioClaveRepository.findAll());
        return "panel-usuarios";
    }

    @GetMapping("/panel/movimientos")
    public String panelMovimientos(Model model) {
        model.addAttribute("movimientos", movimientoRepository.findAll());
        return "panel-movimientos";
    }

    @GetMapping("/panel/cuotas")
    public String panelCuotas(Model model) {
        model.addAttribute("cuotas", cuotaCreditoSocioRepository.findAll());
        return "panel-cuotas-credito";
    }
}
