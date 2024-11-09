import React, {useState} from 'react';

import {IonButton, IonInput, IonCol} from "@ionic/react";

import './style/Register.css';

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
        <IonCol class="ion-col" size="5" >
            <IonInput value={firstName} class="ion-input" placeholder="First Name" type="text" onIonChange={e=>setFirstName(e.detail.value!)}></IonInput>
            <IonInput value={lastName} class='ion-input' placeholder="Last Name" type="text" onIonChange={e=>setLastName(e.detail.value!)}></IonInput>
            <IonInput value={email} class='ion-input' placeholder="Email" type="email" onIonChange={e=>setEmail(e.detail.value!)}></IonInput>
            <IonInput value={password} class='ion-input' placeholder="Password" type="password" onIonChange={e=>setPassword(e.detail.value!)}></IonInput>
            <IonInput value={confirmPassword} class='ion-input' placeholder="Confirm Password" type="password" onIonChange={e=>setConfirmPassword(e.detail.value!)}></IonInput>
            <IonButton expand="block" className='ion-button' onClick={handleRegister}>Register</IonButton>
        </IonCol>
        </div>
    );
}

export default Register;