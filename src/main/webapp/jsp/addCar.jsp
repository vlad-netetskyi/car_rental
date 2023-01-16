<html>
    <body>
        <form action = "/addCar" method = "POST" enctype="multipart/form-data">
           brand: <input type = "text" name = "brand"/><br/>
           model: <input type = "text" name = "model"/><br/>
           image: <input type="file" name="car_img"/><br/>
           <input type = "submit" value = "add", name = "add"/>
        </form>
    </body>
</html>