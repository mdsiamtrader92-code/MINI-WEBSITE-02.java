import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MiniWebsite {

    public static void main(String[] args) throws IOException {
        // ???? ?????? ????? ??????? ????
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // ??? ????? ???? ???? ?????
        server.createContext("/", new HomeHandler());
        server.createContext("/products", new ProductsHandler());

        server.setExecutor(null);
        System.out.println("Server started on http://localhost:8080");
        server.start();
    }

    // ?. ????? ???: ??? ???
    static class HomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = 
                "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "   <meta charset='UTF-8'>" +
                "   <title>Home - Welcome</title>" +
                "   <style>" +
                "       body { font-family: Arial, sans-serif; background: #f4f7f6; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; text-align: center; }" +
                "       .card { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }" +
                "       h1 { color: #333; margin-bottom: 20px; }" +
                "       .btn { display: inline-block; padding: 12px 25px; background-color: #007bff; color: white; text-decoration: none; border-radius: 25px; font-weight: bold; font-size: 16px; transition: 0.3s; }" +
                "       .btn:hover { background-color: #0056b3; }" +
                "   </style>" +
                "</head>" +
                "<body>" +
                "   <div class='card'>" +
                "       <h1>Welcome to our page</h1>" +
                "       <a href='/products' class='btn'>Check our products</a>" +
                "   </div>" +
                "</body>" +
                "</html>";

            sendResponse(exchange, html);
        }
    }

    // ?. ??????? ???: ????????? ???
    static class ProductsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = 
                "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "   <meta charset='UTF-8'>" +
                "   <title>Our Products</title>" +
                "   <style>" +
                "       body { font-family: Arial, sans-serif; background: #f4f7f6; padding: 40px; margin: 0; text-align: center; }" +
                "       h1 { color: #333; margin-bottom: 30px; }" +
                "       .product-container { display: flex; justify-content: center; gap: 20px; flex-wrap: wrap; }" +
                "       .product-card { background: white; border-radius: 10px; padding: 20px; width: 220px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); text-align: center; }" +
                "       .product-card img { width: 100%; height: 140px; object-fit: contain; border-radius: 8px; }" +
                "       .product-title { font-size: 18px; font-weight: bold; margin: 15px 0 5px; color: #222; }" +
                "       .price { color: #28a745; font-size: 16px; font-weight: bold; margin-bottom: 15px; }" +
                "       .buy-btn { background-color: #ff9900; color: white; border: none; padding: 10px 18px; border-radius: 5px; cursor: pointer; font-weight: bold; width: 100%; }" +
                "       .buy-btn:hover { background-color: #e68a00; }" +
                "       .back-btn { display: inline-block; margin-top: 30px; color: #007bff; text-decoration: none; font-weight: bold; }" +
                "   </style>" +
                "</head>" +
                "<body>" +
                "   <h1>Our Exclusive Products</h1>" +
                "   <div class='product-container'>" +

                "       <!-- Item 1 -->" +
                "       <div class='product-card'>" +
                "           <img src='https://cdn-icons-png.flaticon.com/512/428/428001.png' alt='Laptop'>" +
                "           <div class='product-title'>Gaming Laptop</div>" +
                "           <div class='price'>$999</div>" +
                "           <button class='buy-btn' onclick=\"alert('Thank you for purchasing Gaming Laptop!')\">Purchase Now</button>" +
                "       </div>" +

                "       <!-- Item 2 -->" +
                "       <div class='product-card'>" +
                "           <img src='https://cdn-icons-png.flaticon.com/512/0/191.png' alt='Phone'>" +
                "           <div class='product-title'>Smart Phone</div>" +
                "           <div class='price'>$599</div>" +
                "           <button class='buy-btn' onclick=\"alert('Thank you for purchasing Smart Phone!')\">Purchase Now</button>" +
                "       </div>" +

                "       <!-- Item 3 -->" +
                "       <div class='product-card'>" +
                "           <img src='https://cdn-icons-png.flaticon.com/512/27/27127.png' alt='Headphones'>" +
                "           <div class='product-title'>Headphones</div>" +
                "           <div class='price'>$149</div>" +
                "           <button class='buy-btn' onclick=\"alert('Thank you for purchasing Headphones!')\">Purchase Now</button>" +
                "       </div>" +

                "   </div>" +
                "   <br><a href='/' class='back-btn'>&larr; Back to Home</a>" +
                "</body>" +
                "</html>";

            sendResponse(exchange, html);
        }
    }

    // ??????? ??????? ???? ??? ????
    private static void sendResponse(HttpExchange exchange, String html) throws IOException {
        byte[] responseBytes = html.getBytes("UTF-8");
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }
}