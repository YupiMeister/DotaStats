package bmstu.badplayers.dotastats.steamweb;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;

/**
 * Demonstrates the SteamOpenID login class.
 */
public class WebThread implements Runnable {
    private final SteamOpenID openid = new SteamOpenID();

    private String getFullUrl(Request request, String path) {
        StringBuilder builder = new StringBuilder(request.host());
        builder.insert(0, "http://");
        builder.append(path);
        return builder.toString();
    }

    @Override
    public void run() {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                String id = request.session(true).attribute("steamid");
                StringBuilder bodyString = new StringBuilder();
                if (id != null) {
                    bodyString.append("<h2>Welcome ");
                    bodyString.append(id);
                    bodyString.append("</h2>");
                    bodyString.append("<a href=\"logout\">Logout</a>");
                } else {
                    bodyString.append("<a href=\"trade\">Login</a>");
                }

                return bodyString.toString();
            }
        });
        get(new Route("/trade") {
            @Override
            public String handle(Request request, Response response) {
                response.redirect(openid.login(getFullUrl(request, "/auth")));
                // We should never return here.
                // The OpenID login provider should take us somewhere else!
                halt(403, "Go Away!");
                return null;
            }
        });
        get(new Route("/logout") {
            @Override
            public String handle(Request request, Response response) {
                request.session(true).removeAttribute("steamid");
                response.redirect(openid.login(getFullUrl(request, "/")));
                return null;
            }
        });
        get(new Route("/auth") {
            @Override
            public String handle(Request request, Response response) {
                String user = openid.verify(request.url(), request.queryMap().toMap());
                String fullUrl = getFullUrl(request, "/");
                if (user == null) {
                    response.redirect(fullUrl);
                }
                request.session(true).attribute("steamid", user);
                response.redirect(fullUrl);
                return null;
            }
        });
    }
}