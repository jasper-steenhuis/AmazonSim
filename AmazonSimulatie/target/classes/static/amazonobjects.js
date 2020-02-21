function AORobot() {
    var cubeMaterials = [
        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_side.png"), side: THREE.DoubleSide }), //LEFT
        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_side.png"), side: THREE.DoubleSide }), //RIGHT
        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_top.png"), side: THREE.DoubleSide }), //TOP
        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_bottom.png"), side: THREE.DoubleSide }), //BOTTOM
        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_front.png"), side: THREE.DoubleSide }), //FRONT
        new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/robot_front.png"), side: THREE.DoubleSide }), //BACK
    ];
    var group = AO(0.9, 0.3, 0.9, cubeMaterials);
    return group;
}

function AOVrachtwagen() {
    var cubeMaterials = [
            new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/Vrachtauto/links.png"), side: THREE.DoubleSide }), //LEFT
            new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/Vrachtauto/rechts.png"), side: THREE.DoubleSide }), //RIGHT
            new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/Vrachtauto/boven.png"), side: THREE.DoubleSide }), //TOP
            new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/Vrachtauto/boven.png"), side: THREE.DoubleSide }), //BOTTOM
            new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/Vrachtauto/voor.png"), side: THREE.DoubleSide }), //FRONT
            new THREE.MeshBasicMaterial({ map: new THREE.TextureLoader().load("textures/Vrachtauto/achter.png"), side: THREE.DoubleSide }), //BACK
        ];
        var group = AO(3, 3, 9, cubeMaterials);
        return group;
}

function AOStelling() {
var loader = new THREE.OBJLoader();
var group = new THREE.Group();
    loader.load(
	'3DModels/eb_metal_shelf_01.obj',
	    function ( object ) {
		    group.add( object );
		    group.scale.set(0.01, 0.01, 0.01);
	    }
    );
    	    return group;
}

function AO(width, height, depth, cubeMaterials) {
    var geometry = new THREE.BoxGeometry(width, height, depth);
        var material = new THREE.MeshFaceMaterial(cubeMaterials);
        var robot = new THREE.Mesh(geometry, material);
        robot.position.y = height / 2;

        var group = new THREE.Group();
        group.add(robot);
        return group;
}