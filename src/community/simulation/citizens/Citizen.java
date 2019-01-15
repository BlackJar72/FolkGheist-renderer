package community.simulation.citizens;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.Selectable;
import community.simulation.map.Locatable;
import community.simulation.Simulation;
import community.simulation.map.Location;
import community.simulation.citizens.relationships.*;
import community.simulation.citizens.enums.*;
import community.simulation.locales.*;
import java.util.EnumSet;
import community.simulation.citizens.ai.*;
import community.util.BonusHelper;
import static community.ui.UI.*;
import static community.ui.test.TestingStuff.drawPeep;
import static community.simulation.citizens.AI.*;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Citizen implements Locatable, Selectable {
    public static int number = 0;
    public boolean selected;
    public Simulation city;  
    private float speed = 0.025f;
    private int id;
    private Location location;
    private Locale home;
    private Locale employer;
    private Locale locale;       // Building currently in, if any
    private boolean male;  
    private boolean married;
    private int age;
    private EnumSet<Interests> interests;
    
    //These are public since they're primary purpose is code organization
    //into managable namespaces and they have there own encapsulation.
    public Personality personality;
    public State state;
    public Relationships relationships;
    public SES ses;
    public GoalPhase cycle = GoalPhase.HOME;
    
    //TODO: Type for travelMode
    Locale destination;
    // TODO: type for destPurpose
    Movement route;
    Citizen leader;  // Another citizen who is currently being followed
    int stayTimer;   // Tics left in currect location
    
    
    public Citizen(Simulation city/*TODO*/) {
        id = number;
        number++;
        this.city = city;
        // Totally(?) temp stand in
        // id = ???;
        location = new Location((float)(Math.random() * 64f) - 32f,
                0.0f,
                (float) (Math.random() * 64f) - 32f,
                (float) Math.random() * 360);
//        location = new Location((float)(Math.random() * 512f) - 256f,
//                0.0f,
//                (float) (Math.random() * 512f) - 256f,
//                (float) Math.random() * 360);
        for(Locale loc: city.places) {
            if(loc.collide2d(location)) locale = loc;
        }
        male = (((int) (Math.random() * 2f)) == 1);
        age  = 64;
        ses  = new SES();
        personality = new Personality();
        speed += (((float)BonusHelper.get(personality.getNeurotic())) / 1000f);
        speed += (((float)BonusHelper.get(personality.getExtro())) / 1000f);
        state = new State();
        cycle = GoalPhase.HOME;
        stayTimer = 60;  
        
        route = new Wandering(this);
        
        //TODO: A lot, and how much shold be done here?  What should be 
        //passed in.  Will do when more is fleshed out elsewhere.
    }
    
    
    
    
    
    public void frame() {
        if((route != null) && (stayTimer <= 0)) move();
        draw();
    }
    
    
    public void tick() {
        if(stayTimer > 0) {
            stayTimer--;
            return;
        }       // Waiting
        if(route instanceof Wandering) route.tick();
        else {
            if(route == null) {
                destination = (Locale) chooseDest(this);
                if(destination == null) {
                    stayTimer = 150;
                    return;
                }
                route = new Wandering(this);
                //route = Router.makeRoute(location, destination, this);
                //route = Router.makeLameRoute(location, destination, this);
            }
            else move();
        }
        //TODO
    }
    
    
    void second() {
        //TODO
    }
    
   
    void daily() {
        //TODO
    }
    
    
    void weekly() {
        //TODO
    }
    
    
    public void draw() {
        //if(route != null) move();
        drawPeep(location);
    }
    
    
    public void move() {
        if(route == null || !route.isValid()) return;
        location.change(route.vX() * getdelta(), 0,
                        route.vZ() * getdelta(), 
                        route.getAngle());
        if(route.update(location)) {            
            locale = destination;
            locale.beVisited(this);
            route = null;
            stayTimer = 30;
        } // Use returned boolean later
    }
    
    
    public void nextCylce() {
        if(cycle == GoalPhase.HOME) cycle = GoalPhase.WORK;
        else if(cycle == GoalPhase.WORK) cycle = GoalPhase.FUN;
        else if(cycle == GoalPhase.FUN) cycle = GoalPhase.HOME;
        else community.util.HouseKeeping.reportError("Has invalid cycle; "
                + "trying to set outside enum bounds in nextCycle().");
    }
    
    
    
    
    
    
    /*****************************************/
    /*        GETTERS & SETTERS              */ 
    /*****************************************/

    
    public int getAge() {
        return age;
    }
    

    public void setAge(int age) {
        this.age = age;
    }
    

    public Simulation getCity() {
        return city;
    }
    

    public void setCity(Simulation city) {
        this.city = city;
    }
    

    public Locale getDestination() {
        return destination;
    }
    

    public void setDestination(Locale destination) {
        this.destination = destination;
    }
    

    public Locale getEmployer() {
        return employer;
    }
    

    public void setEmployer(Locale employer) {
        this.employer = employer;
    }
    

    public Locale getHome() {
        return home;
    }
    

    public void setHome(Locale home) {
        this.home = home;
    }
    

    public EnumSet<Interests> getInterests() {
        return interests;
    }
    

    public void setInterests(EnumSet<Interests> interests) {
        this.interests = interests;
    }
    

    public Citizen getLeader() {
        return leader;
    }
    

    public void setLeader(Citizen leader) {
        this.leader = leader;
    }
    

    public boolean isMale() {
        return male;
    }
    
    

    public boolean isMarried() {
        return married;
    }
    

    public void setMarried(boolean married) {
        this.married = married;
    }
    

    public Personality getPersonality() {
        return personality;
    }
    

    public Relationships getRelationships() {
        return relationships;
    }
    

    public Movement getRoute() {
        return route;
    }
    

    public void setRoute(Movement route) {
        this.route = route;
    }
    

    public SES getSes() {
        return ses;
    }
    

    public void setSes(SES ses) {
        this.ses = ses;
    }
    

    public State getState() {
        return state;
    }
    

    public void setState(State state) {
        this.state = state;
    }
    
    
    public int getID() {
        return id;
    }
    
    
    public Relationships getRelation() {
        return relationships;
    }
    
    
    public Location getLocation() {
        return location;
    }
    

    public float getSpeed() {
        return speed;
    }
    

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getStayTimer() {
        return stayTimer;
    }

    public void setStayTimer(int stayTimer) {
        this.stayTimer = stayTimer;
    }

    public GoalPhase getCycle() {
        return cycle;
    }

    public void setCycle(GoalPhase cycle) {
        this.cycle = cycle;
    }
    

    @Override
    public float getXfloat() {
        return location.getXfloat();
    }
    

    @Override
    public float getYfloat() {
       return location.getYfloat();
    }
    

    @Override
    public float getZfloat() {
        return location.getZfloat();
    }
    

    @Override
    public float getRotate() {
        return location.getRotate();
    }
    

    @Override
    public int getXint() {
        return location.getXint();
    }
    

    @Override
    public int getYint() {
        return location.getYint();
    }
    

    @Override
    public int getZint() {
        return location.getZint();
    }
    

    @Override
    public int getRint() {
        return location.getRint();
    }
    

    @Override
    public boolean setX(float x) {
        return location.setX(x);
    }
    

    @Override
    public boolean setY(float y) {
        return location.setY(y);
    }
    

    @Override
    public boolean setZ(float z) {
        return location.setZ(z);
    }
    

    @Override
    public void setRotate(float r) {
        location.setRotate(r);
    }
    

    @Override
    public void setX(int x) {
        location.setX(x);
    }
    

    @Override
    public void setY(int y) {
        location.setY(y);
    }
    

    @Override
    public void setZ(int z) {
        location.setZ(z);
    }
    

    @Override
    public void setR(int z) {
        location.setR(z);
    }
    

    @Override
    public void select() {
        selected = true;
    }
    

    @Override
    public void deselect() {
        selected = false;
    }
    

    @Override
    public void setSelected(boolean s) {
        selected = s;
    }
    

    @Override
    public boolean isSelected() {
        return selected;
    }
    
    
}
