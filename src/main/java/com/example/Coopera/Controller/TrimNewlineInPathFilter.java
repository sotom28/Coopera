package com.example.Coopera.Controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro defensivo para limpiar saltos de línea codificados ("%0A", "%0D")
 * o espacios accidentales en la URL. Evita 404 cuando se copia/pega la ruta
 * con un Enter al final (por ejemplo: "/api/movimientos%0A").
 */
@Component
public class TrimNewlineInPathFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        String qs = request.getQueryString();

        // Normaliza caracteres de control en la URI
        String cleaned = uri
                .replace("%0A", "")
                .replace("%0D", "")
                .replace("\n", "")
                .replace("\r", "");

        // También revisa la versión decodificada por si vino como "+" o espacios
        String decoded = URLDecoder.decode(cleaned, StandardCharsets.UTF_8);
        String trimmed = decoded.trim();

        // Si hubo cambios, redirige a la ruta limpia (preservando query string)
        if (!trimmed.equals(decoded) || !cleaned.equals(uri)) {
            String target = trimmed;
            if (qs != null && !qs.isBlank()) {
                target += "?" + qs;
            }
            response.sendRedirect(target);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
