package game;

/**
 * Ugly class to build encounters for the Encounter Manager.
 * I'd love to have encounters be readable from JSON, so anyone
 * could just create their own without having to hardcode them, 
 * but I have i) a case of the lazybones, and ii) no time
 */
public class EncounterBuilder {
    public static Encounter[] buildEncounters()
    {
        return new Encounter[] 
        {
            new Encounter(
                "Loot Balloon",
                "You see a gas-filled balloon floating in the distance, with a crate dangling beneath it precariously.",
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Attempt to hook it", 
                        "You hook the balloon, and pull the crate aboard. Opening it reveals... nothing. It's empty. L.", 
                        0, 
                        0),
                    new EncounterOutcome(
                        "Ram into it", 
                        "You decide to sail your airship directly into the crate, at full speed. This, predictably, causes damage to the ship.", 
                        -10, 
                        0
                        )
                }
            ),
            new Encounter(
                "Loot Balloon",
                "You see a gas-filled balloon floating in the distance, with a crate dangling beneath it precariously.",
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Attempt to hook it", 
                        "You hook the balloon, and pull the crate aboard. Opening it reveals a bunch of airship repair supplies. You use it to fortify your ship.", 
                        10, 
                        0),
                    new EncounterOutcome(
                        "Ignore it", 
                        "You try to ignore it, but a gust of wind pushes the crate into your airship, damaging it.", 
                        -5, 
                        0
                        )
                }
            ),
            new Encounter(
                "Loot Balloon",
                "You see a gas-filled balloon floating in the distance, with a crate dangling beneath it precariously.",
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Attempt to hook it", 
                        "You hook the balloon, and pull the crate aboard. Opening it reveals a bunch of guns. This will be useful in combat!", 
                        0, 
                        25),
                    new EncounterOutcome(
                        "Shoot at it", 
                        "You decided to shoot at it? Why? Uh. You shoot at the balloon, popping it and dropping the crate to the ocean far below.", 
                        0, 
                        0
                        )
                }
            ),
            new Encounter(
                "Bird Strike", 
                "You witness a large bird, dead ahead, flying directly towards you. It appears to have a sharp beak.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Do nothing", 
                        "You watch as the bird, pushed by the strong breeze, torpedoes directly into the ship's forward lift balloon, causing severe damage.",
                        -50, 
                        0
                    ),
                    new EncounterOutcome(
                        "Man the cannons",
                        "You send your crew to battlestations, manning the variety of small arms mounted to the ship. The bird dodges and weaves their bullets, but they eventually land a hit and neutralize the threat.",
                        0, 
                        -20 // wasted bullets or something
                    ),
                    new EncounterOutcome(
                        "Cast a net",
                        "You order the crew to cast a large net, and raise the ship's altitude. The bird crashes into the net, and you have it brought onboard and sent to the cook.",
                        5, // crew morale buff? 
                        0
                    ),   
                }
            ),
            new Encounter(
                "Bird Strike", 
                "You witness a large bird, dead ahead, flying directly towards you. It appears to have a sharp beak.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Do nothing", 
                        "You watch as the bird, pushed by the strong breeze, flies right by the ship. What a majestic sight!",
                        5, // morale boost? this is absurd 
                        0
                    ),
                    new EncounterOutcome(
                        "Whip out your revolver",
                        "You draw your Captain's Revolver, open a window, and aim at the bird. You fire a single bullet. It misses. The bird, now angry, pecks a hole in the airship's delicate skin.",
                        -10, 
                        0
                    ), 
                }
            ),
            new Encounter(
                "Bird Strike", 
                "You witness a large bird, dead ahead, flying directly towards you. It appears to have a sharp beak.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Do nothing", 
                        "You watch as the bird, pushed by the strong breeze, flies right by the ship. An engineer, distracted by the majestic bird, drops a wrench and causes damage to the ship.",
                        -5,
                        0
                    ),
                    new EncounterOutcome(
                        "Whip out your revolver",
                        "You draw your Captain's Revolver, open a window, and aim at the bird. You fire a single bullet. It hits. The bird, now severely wounded, plummets to the ground. You monster.",
                        0, 
                        0
                    ), 
                }
            ),
            new Encounter(
                "A Friendly Airship", 
                "You receive a message from the spotter. An airship, flying a friendly flag, has been spotted to the north.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Carry on", 
                        "No time for pleasantries. We have a pirate to catch.",
                        0,
                        0
                    ),
                    new EncounterOutcome(
                        "Rendezvous with the airship",
                        "You pull up alongside the friendlies, and explain your mission. They, being friendly, offer some supplies to help you in your journey.",
                        10, 
                        10
                    ),
                    new EncounterOutcome(
                        "Whip out your revolver", 
                        "You draw your Captain's Revolver. One of your officers calls you immature, and takes your gun. Your crew hears of this, and is concerned.", 
                        -5, // morale again 
                        0)
                }
            ),
            new Encounter(
                "A Friendly Airship", 
                "You receive a message from the spotter. An airship, flying a friendly flag, has been spotted to the north.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Carry on", 
                        "No time for pleasantries. We have a pirate to catch.",
                        0,
                        0
                    ),
                    new EncounterOutcome(
                        "Rendezvous with the airship",
                        "You pull up alongside the friendlies, and explain your mission. They turn out to be pro-pirate, and shoot a hole in your aft lifting balloon.",
                        -10, 
                        0
                    ),
                    new EncounterOutcome(
                        "Ram them", 
                        "This is a terrible idea, but if it's what you want to do... You decide to ram the friendly ship, at full speed. This does an insane amount of damage to both of you.", 
                        -95, 
                        0
                    )
                }
            ),
            new Encounter(
                "An Unknown Airship", 
                "From the clouds emerges another airship, flying no flags. You feel uneasy as it approaches ever closer.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Keep calm and carry on", 
                        "You try to sneak by the unknown airship. Just as you think you're in the clear, you hear the impacts of their rounds on your ship. You drop ballast, increase thrust, and escape from the scene, damaged but alive.",
                        -60,
                        -30
                    ),
                    new EncounterOutcome(
                        "Raise a pirate flag",
                        "You raise a pirate flag, and wait for the unknown ship's reaction. Moments later, they raise a pirate flag of their own, and quickly leave the scene.",
                        0, 
                        0
                    ),
                    new EncounterOutcome(
                        "Battlestations", 
                        "You order the crew to battlestations, and engage the unknown ship. You catch them off guard, and are able to cause severe damage. They frantically escape, just barely.", 
                        0, 
                        25
                    )
                }
            ),
            new Encounter(
                "An Unknown Airship", 
                "From the clouds emerges another airship, flying no flags. You feel uneasy as it approaches ever closer.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Keep calm and carry on", 
                        "You choose to ignore the ship, and continue on your flight path. They don't seem to mind.",
                        0,
                        0
                    ),
                    new EncounterOutcome(
                        "Raise a pirate flag",
                        "You raise a pirate flag, and wait for the unknown ship's reaction. Moments later, they raise a flag of surrender. Feeling ashamed of your actions, you explain the situation to their captain and give them a few weapons.",
                        0, 
                        -5
                    ),
                    new EncounterOutcome(
                        "Battlestations", 
                        "You order the crew to battlestations, and engage the unknown ship. They raise a flag of surrender, and quickly flee the scene.", 
                        0, 
                        -25
                    )
                }
            ),
            new Encounter(
                "Derelict Airship", 
                "You receive a message from the crow's nest atop the ship. Your lookout has spotted a large, seemingly abandoned airship, ominously floating way above. Reaching it is possible, but extremely dangerous due to the altitude.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Ignore it", 
                        "You choose to carry on with your mission, leaving the derelict airship floating high above.",
                        0,
                        0
                    ),
                    new EncounterOutcome(
                        "Ascend! We've got looting to do!",
                        "You ascend, and rendezvous with the airship. You send your crew to grab whatever they can and bring it aboard. You ask them about the ship's crew, but they don't respond.",
                        -15, 
                        20
                    ),
                    new EncounterOutcome(
                        "Ascend! Its crew might need help!", 
                        "You ascend, and rendezvous with the airship. You board the ship, only to find its crew dead - likely from hypoxia or hypothermia.", 
                        -15, 
                        0
                    )
                }
            ),
            new Encounter(
                // this one's a little much, particularly outcome #1.
                "Derelict Airship", 
                "You receive a message from the crow's nest atop the ship. Your lookout has spotted a large, seemingly abandoned airship, ominously floating way above. Reaching it is possible, but extremely dangerous due to the altitude.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Ignore it", 
                        "Just as you decide to carry on with your mission, the lookout reports a series of objects falling from the ship. As they fall by you, you get a glimpse of a person, frantically gesturing for help.",
                        -10, // morale debuff for not helping
                        0
                    ),
                    new EncounterOutcome(
                        "Investigate the ship, from a distance",
                        "You ascend slightly, to get a better glimpse of the derelict ship. You notice people waving frantically, and immediately rendezvous with the ship. Once aboard, they offer to help you in your journey as thanks.",
                        -5,  
                        10
                    ),
                    new EncounterOutcome(
                        "Board the ship and look for survivors", 
                        "You ascend, and rendezvous with the airship. Upon boarding, you find survivors and help them board your ship. They inform you of a weapon cache, which you promptly collect before moving on.", 
                        -15, 
                        25
                    )
                }
            ),
            new Encounter(
                "???", 
                "Looking ahead, you see dark storm clouds with strange purple lightning. You decide to descend to fly under the clouds, just above the surface of the ocean. As you fly along, you find a barge with a bus on it.", 
                new EncounterOutcome[] {
                    new EncounterOutcome(
                        "Ignore it", 
                        "A peculiar sight, but we don't have time for this.",
                        0,
                        0
                    ),
                    new EncounterOutcome(
                        "Investigate",
                        "You approach the barge, and rappel down to investigate. You notice a strange assortment of devices that make very little sense to you. The bus is unlike any you've ever seen, and appears to have been heavily modified. You find a box full of strange, but basic weaponry, and take it.",
                        0,  
                        25
                    )
                }
            ),
        };
    } 
}
