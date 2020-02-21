function parseCommand(input = "") {
    return JSON.parse(input);
}

var socket;

window.onload = function () {
    var camera, scene, renderer;
    var cameraControls;
    var threeFrame;

    var worldObjects = {};

    function init() {
        renderer = new THREE.WebGLRenderer({ antialias: true });
        renderer.setPixelRatio(window.devicePixelRatio);
        renderer.setSize(window.innerWidth, window.innerHeight + 5);
        document.body.appendChild(renderer.domElement);

        camera = new THREE.PerspectiveCamera(30, window.innerWidth / window.innerHeight, 1, 1000);
        cameraControls = new THREE.OrbitControls(camera, renderer.domElement);
        camera.position.z = 50;
        camera.position.y = 50;
        camera.position.x = 100;
        cameraControls.update();
        scene = new THREE.Scene();

        window.addEventListener('resize', onWindowResize, false);

        var geometry = new THREE.PlaneGeometry(30, 30, 32);
        var texture = new THREE.TextureLoader().load("textures/grid.jpg");
        texture.repeat.set(1, 1);
        texture.wrapS = THREE.RepeatWrapping;
        texture.wrapT = THREE.RepeatWrapping;
        var material = new THREE.MeshBasicMaterial({ map: texture, side: THREE.DoubleSide });
        var plane = new THREE.Mesh(geometry, material);
        plane.rotation.x = Math.PI / 2.0;
        plane.position.x = 15;
        plane.position.z = 15;
        scene.add(plane);

        var light = new THREE.AmbientLight(0x404040);
        light.intensity = 4;
        scene.add(light);
    }

    function onWindowResize() {
        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();
        renderer.setSize(window.innerWidth, window.innerHeight);
    }

    function animate() {
        requestAnimationFrame(animate);
        cameraControls.update();
        renderer.render(scene, camera);
    }

    /*
     * Hier wordt de socketcommunicatie geregeld. Er wordt een nieuwe websocket aangemaakt voor het webadres dat we in
     * de server geconfigureerd hebben als connectiepunt (/connectToSimulation). Op de socket wordt een .onmessage
     * functie geregistreerd waarmee binnenkomende berichten worden afgehandeld.
     */
    socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/connectToSimulation");
    socket.onmessage = function (event) {
        //Hier wordt het commando dat vanuit de server wordt gegeven uit elkaar gehaald
        var command = parseCommand(event.data);

        //Wanneer het commando is "object_update", dan wordt deze code uitgevoerd. Bekijk ook de servercode om dit goed te begrijpen.
        if (command.command == "object_update") {
            //Wanneer het object dat moet worden geupdate nog niet bestaat (komt niet voor in de lijst met worldObjects op de client),
            //dan wordt het 3D model eerst aangemaakt in de 3D wereld.
            if (Object.keys(worldObjects).indexOf(command.parameters.uuid) < 0) {

                /*
                 *  Get the right object
                 */
                switch(command.parameters.type) {
                    case "robot":
                        var group = AORobot();
                        break;
                    case "vrachtwagen":
                        var group = AOVrachtwagen();
                        break;
                    case "stelling":
                        var group = AOStelling();
                        break;
                }
                switch(command.parameters.type) {

                }


                scene.add(group);
                worldObjects[command.parameters.uuid] = group;
            }

            /*
             * Deze code wordt elke update uitgevoerd. Het update alle positiegegevens van het 3D object.
             */
            var object = worldObjects[command.parameters.uuid];

            object.position.x = command.parameters.x;
            object.position.y = command.parameters.y;
            object.position.z = command.parameters.z;

            object.rotation.x = command.parameters.rotationX;
            object.rotation.y = command.parameters.rotationY;
            object.rotation.z = command.parameters.rotationZ;
        }
    }

    init();
    animate();
}