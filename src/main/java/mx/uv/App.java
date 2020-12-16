package mx.uv;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


/**
 * Ejemplo con el motor
 * de plantillas Velocity
 */
public class App 
{
    public static void main( String[] args )
    {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("hola", "Mundo de Velocity");
            modelo.put("personita", new Persona("Charles"));
            return new ModelAndView(modelo, "index.vm");
        }, new VelocityTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
