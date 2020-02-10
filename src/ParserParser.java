import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 @author Kristina
*/ 

public class ParserParser {

    private String id;

    private String dt;

    private Clouds clouds;

    private Coord coord;

    private Wind wind;

    private String cod;

    private Sys sys;

    private String name;

    private Weather[] weather;

    private Rain rain;

    private Main main;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public Clouds getClouds ()
    {
        return clouds;
    }

    public void setClouds (Clouds clouds)
    {
        this.clouds = clouds;
    }

    public Coord getCoord ()
    {
        return coord;
    }

    public void setCoord (Coord coord)
    {
        this.coord = coord;
    }

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    public String getCod ()
    {
        return cod;
    }

    public void setCod (String cod)
    {
        this.cod = cod;
    }

    public Sys getSys ()
    {
        return sys;
    }

    public void setSys (Sys sys)
    {
        this.sys = sys;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }

    public Rain getRain ()
    {
        return rain;
    }

    public void setRain (Rain rain)
    {
        this.rain = rain;
    }

    public Main getMain ()
    {
        return main;
    }

    public void setMain (Main main)
    {
        this.main = main;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", dt = "+dt+", clouds = "+clouds+", coord = "+coord+", wind = "+wind+", cod = "+cod+", sys = "+sys+", name = "+name+", weather = "+weather+", rain = "+rain+", main = "+main+"]";
    }

	/*
	 * {"coord":{"lon":139,"lat":35},
	 * "sys":{"country":"JP","sunrise":1369769524,"sunset":1369821049},
	 * "weather":[{"id":804,"main":"clouds","description":"overcast clouds","icon":
	 * "04n"}],
	 * "main":{"temp":289.5,"humidity":89,"pressure":1013,"temp_min":287.04,
	 * "temp_max":292.04}, "wind":{"speed":7.31,"deg":187.002}, "rain":{"3h":0},
	 * "clouds":{"all":92}, "dt":1369824698, "id":1851632, "name":"Shuzenji",
	 * "cod":200}
	 */
}
