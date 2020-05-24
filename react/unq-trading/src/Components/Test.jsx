import React, { Component } from 'react';
import axios from 'axios';
import { Button, Form, Col } from 'react-bootstrap';

export default class Test extends Component {

        constructor(props) {
            super(props);
            this.state = {
                testField: ''
            };
            this.handleChange = this.handleChange.bind(this);
        }

    handleChange(value, prop) {
        this.setState(prevState => ({ ...prevState, [prop]: value }));
    }

    sent = (ev) => {
    axios.post(`http://localhost:8080/api/test/save`, {
                                        id: 1,
                                        testField: this.state.testField
                                    }).then((response) => {
                                        alert("ok")
                                    }).catch((error) => {
                                        alert("fail")
                                    })
                        }

    render() {
        return (

        <div>
        <Form>
          <Form.Row>
            <Col>
              <Form.Control placeholder="Ingrese texto de prueba" onChange={event => this.handleChange(event.target.value, 'testField')} />
            </Col>
            <Button variant="primary" onClick={ev => this.sent(ev)}>Enviar</Button>{' '}
          </Form.Row>
        </Form>
        </div>
            );
    }
}

