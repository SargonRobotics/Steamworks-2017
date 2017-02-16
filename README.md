# Steamworks-2017
This is our code for the 2017 game FIRST Steamworks.
Our code this year is written in Java, because of its nature to be slightly more user friendly, expecially with streams.

## Vision
This is the first year our team has managed to get vision tracking with GRIP (Graphically Represented Image Processing engine).

With our vision code we can
 * Center our robot in accordance to the reflective tape
 * Find the distance from the camera to the reflective tape (with an average percent error of about +-5%)
 
## Drive
 This years drive code is a bit more unique.
 This years code implements our own custom written drive method that enables us to drive while turning.
 > And give our main programmer a massive headache (thanks Noah)
 
## Shooter
 This year shooter code doesn't break any records, but it's a good example to how command groups work.

## Autonomous
Our goal for our autonomous code is to deposit a gear onto the airship. We'll have one command for each starting station.
We're using ultrasonic sensors to position our robot during autonomous.

## Todo
A list of things our programming team is currently working on
- [ ] Fix our out of range problem with the ultrasonic sensors
- [ ] Find correct distances to use during auto
- [ ] Find offset for centering the robot based on the shooter
- [x] Find correct shooting speed
- [ ] Test our drive code more in depth
