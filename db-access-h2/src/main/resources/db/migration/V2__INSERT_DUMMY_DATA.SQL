INSERT INTO smart_farm.farms (name) VALUES ('main_farm');

INSERT INTO smart_farm.statuses (name) VALUES ('On');
INSERT INTO smart_farm.statuses (name) VALUES ('Off');

INSERT INTO smart_farm.nodes_control (name, location, farm, status)
    VALUES ('test_control_node_1', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_control (name, location, farm, status)
    VALUES ('test_control_node_2', 'POINT(30.259669 32.419143)', 1, 1);

INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_01', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_02', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_03', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_04', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_05', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_06', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_07', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_08', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_09', 'POINT(30.259669 32.419143)', 1, 1);
INSERT INTO smart_farm.nodes_sensor (name, location, farm, status)
    VALUES ('test_sensor_node_10', 'POINT(30.259669 32.419143)', 1, 1);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 86);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 83);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 82);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 84);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 83);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 87);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 86);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 29, 15, 88);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 83);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 86);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 28.2, 15.2, 85);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 27.6, 15.3, 85.3);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 27.1, 15.5, 85.2);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 26.5, 15.7, 85.3);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 25.8, 16, 85.1);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 25.2, 16.4, 84.8);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 24.7, 16.7, 85.2);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 24, 17, 85);

INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (1, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (2, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (3, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (4, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (5, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (6, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (7, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (8, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (9, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);
INSERT INTO smart_farm.readings (sensor_node, time_stamp, temperature, humidity, moisture)
    VALUES (10, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 23.4, 17.4, 84.9);


INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 12);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586028900, DATE '1970-01-01'), 12.1);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 12.2);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586030700, DATE '1970-01-01'), 12.1);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 12.23);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586032500, DATE '1970-01-01'), 12.21);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 12.21);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586034300, DATE '1970-01-01'), 12.23);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 12.23);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586036100, DATE '1970-01-01'), 12.24);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 12.26);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586037900, DATE '1970-01-01'), 12.26);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 12.27);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586039700, DATE '1970-01-01'), 12.25);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 12.27);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586041500, DATE '1970-01-01'), 12.26);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 12.28);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586043300, DATE '1970-01-01'), 12.29);

INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (1, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 12.3);
INSERT INTO smart_farm.signals (control_node, time_stamp, sig_value)
    VALUES (2, DATEADD('SECOND', 1586045100, DATE '1970-01-01'), 12.29);