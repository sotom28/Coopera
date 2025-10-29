package com.example.Coopera.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import com.example.Coopera.Modelo.Socio;
import com.example.Coopera.Modelo.Usuario_Clave;
import com.example.Coopera.Repository.SocioRepository;
import com.example.Coopera.Repository.UsuarioClaveRepository;

@Service
public class SocioService {

    private final SocioRepository repo;
    private final UsuarioClaveRepository usuarioClaveRepository;

    public SocioService(SocioRepository repo, UsuarioClaveRepository usuarioClaveRepository) {
        this.repo = repo;
        this.usuarioClaveRepository = usuarioClaveRepository;
    }

    public List<Socio> findAll() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "numeroSocio"));
    }

    public Optional<Socio> findById(Long id) {
        return repo.findById(id);
    }

    public Socio save(Socio socio) {
        return repo.save(socio);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    public Usuario_Clave saveSocioAndCreateUsuarioClave(Socio socio) {
        // Guardar socio primero
        Socio saved = repo.save(socio);

        // Construir datos para usuario_clave
        Usuario_Clave u = new Usuario_Clave();
        if (saved.getNumeroSocio() != null) {
            u.setNroSocio(saved.getNumeroSocio().intValue());
        }

        String rut = buildRut(saved);
        u.setNumrunSocio(rut);

        String nombreCompleto = buildNombreCompleto(saved);
        u.setNombreSocio(nombreCompleto);

        String nombreUsuario = generateUsername(saved);
        u.setNombreUsuario(nombreUsuario);

        String clave = generatePassword(10);
        u.setClaveUsuario(clave);

        return usuarioClaveRepository.save(u);
    }

    private String buildRut(Socio s) {
        String num = s.getNumrun() != null ? String.valueOf(s.getNumrun()) : "";
        String dv = s.getDvrun() != null ? s.getDvrun() : "";
        if (!num.isEmpty() && !dv.isEmpty()) return num + "-" + dv;
        return num.isEmpty() ? dv : num; // fallback
    }

    private String buildNombreCompleto(Socio s) {
        String p = s.getPnombre() != null ? s.getPnombre() : (s.getPnombre() == null && hasFieldNamedWithUppercase("Pnombre", s) ? s.getPnombre() : "");
        String sN = s.getSnombre() != null ? s.getSnombre() : "";
        String ap = s.getAppaterno() != null ? s.getAppaterno() : "";
        String am = s.getApmaterno() != null ? s.getApmaterno() : "";
        return String.join(" ", new String[]{p, sN, ap, am}).replaceAll("\\s+", " ").trim();
    }

    // Helper to avoid warning about unusual field naming; always returns false but keeps code explicit
    private boolean hasFieldNamedWithUppercase(String name, Socio s){ return false; }

    private String generateUsername(Socio s) {
        String p = s.getPnombre() != null ? s.getPnombre() : "";
        String ap = s.getAppaterno() != null ? s.getAppaterno() : "";
        String base = (firstLetter(p) + normalize(ap)).toLowerCase(Locale.ROOT);
        if (base.isBlank()) {
            String rut = s.getNumrun() != null ? String.valueOf(s.getNumrun()) : "usuario";
            base = ("u" + rut);
        }
        // limitar a 20 chars
        base = base.length() > 20 ? base.substring(0, 20) : base;
        return base;
    }

    private String firstLetter(String s){ return (s == null || s.isEmpty()) ? "" : s.substring(0,1); }

    private String normalize(String s){
        if (s == null) return "";
        String n = java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD);
        return n.replaceAll("[^A-Za-z0-9]", "");
    }

    private String generatePassword(int len) {
        final String upper = "ABCDEFGHJKLMNPQRSTUVWXYZ";
        final String lower = "abcdefghijkmnpqrstuvwxyz";
        final String digits = "23456789";
        final String all = upper + lower + digits;
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        // garantizar al menos un char de cada tipo
        sb.append(upper.charAt(rnd.nextInt(upper.length())));
        sb.append(lower.charAt(rnd.nextInt(lower.length())));
        sb.append(digits.charAt(rnd.nextInt(digits.length())));
        for (int i = 3; i < len; i++) {
            sb.append(all.charAt(rnd.nextInt(all.length())));
        }
        return sb.toString();
    }
}
