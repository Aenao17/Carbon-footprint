import React, {useState} from 'react';

import {IonButton, IonInput, IonItem, IonLabel, IonCol} from "@ionic/react";

const Register = () => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleRegister = async () => {
        console.log('Register');
        if (firstName === '' || lastName === '' || email === '' || password === '' || confirmPassword === '') {
            alert('Please enter all fields');
            return;
        }
        if (password !== confirmPassword) {
            alert('Passwords do not match');
            return;
        }
        try {
            const response = await fetch('http://localhost:5000/api/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({firstName, lastName, email, password})
            });
            if (!response.ok) {
                throw new Error('Register failed');
            }
            const data = await response.json();
            } catch (error) {
            console.error('Register failed', error);
            alert('Register failed');
        }
    }

    return (
        <div className="flex-center">
        <IonCol size="5" >
            <IonInput value={firstName} placeholder="First Name" type="text"></IonInput>
            <IonInput value={lastName} placeholder="Last Name" type="text"></IonInput>
            <IonInput value={email} placeholder="Email" type="email"></IonInput>
            <IonInput value={password} placeholder="Password" type="password"></IonInput>
            <IonInput value={confirmPassword} placeholder="Confirm Password" type="password"></IonInput>
            <IonButton expand="block" onClick={handleRegister}>Register</IonButton>
        </IonCol>
        </div>
    );
}

export default Register;