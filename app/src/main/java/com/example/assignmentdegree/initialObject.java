package com.example.assignmentdegree;

import java.util.ArrayList;

public class initialObject {

    public static ArrayList<Movie> getList(){
        ArrayList<Movie> arrayList = new ArrayList<>();
        arrayList.add(new Movie(R.drawable.aladdin,"Aladdin","Aladdin is a street urchin who lives in a large and busy town long ago with his faithful monkey friend Abu. When Princess Jasmine gets tired of being forced to remain in the palace that overlooks the city, she sneaks out to the marketplace, where she accidentally meets Aladdin."));
        arrayList.add(new Movie(R.drawable.frozen_2,"Frozen 2","FROZEN 2 opens with a flashback to the king (voiced by Alfred Molina) and queen (Evan Rachel Wood) of Arendelle telling young Elsa and Anna a bedtime story about a magical enchanted forest and the indigenous people of Northuldra who live there, along with the elemental spirits of air, water, fire, and earth."));
        arrayList.add(new Movie(R.drawable.joker,"Joker","Joaquin Phoenix as Arthur Fleck / Joker: A mentally ill, impoverished stand-up comedian disregarded by society, whose history of abuse causes him to become a nihilistic criminal"));
        arrayList.add(new Movie(R.drawable.jumaji_2,"Jumaji 2","Jumanji: Welcome to the Jungle is a 2017 American fantasy adventure comedy film directed by Jake Kasdan and written by Chris McKenna, Erik Sommers, Scott Rosenberg, and Jeff Pinkner, based on a story by McKenna."));
        arrayList.add(new Movie(R.drawable.spider_man_far_from_the_home,"SpiderMan:far from home","Spider-Man: Far From Home is a 2019 American superhero film based on the Marvel Comics character Spider-Man, co-produced by Columbia Pictures and Marvel Studios, and distributed by Sony Pictures Releasing."));

        return arrayList;
    }
}
