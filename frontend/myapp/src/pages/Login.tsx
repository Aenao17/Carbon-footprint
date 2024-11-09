import {
    IonButton,
    IonCard,
    IonCardContent,
    IonCardHeader,
    IonCardTitle,
    IonInput,
    IonItem,
    IonLabel
} from "@ionic/react";
import React, {useState} from "react";
import './style/Login.css';

const Login = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async () => {
        if (email === '' || password === '') {
            alert('Please enter email and password');
            return;
        }
        try {
            const response = await fetch('http://localhost:5000/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({email, password})
            });
            if (!response.ok) {
                throw new Error('Login failed');
            }
            const data = await response.json();
        }catch (error) {
            console.error('Login failed', error);
            alert('Login failed');
        }
    }


    return (
        <div className="flex-center">
            <IonCard>
                <IonCardHeader>
                    <IonCardTitle color="primary">Login</IonCardTitle>
                </IonCardHeader>

                <IonCardContent>
                    <IonItem>
                        <IonLabel position="stacked" class="label">Email</IonLabel>
                        <IonInput value={email} type="email" class="input-group" onIonChange={e=>setEmail(e.detail.value!)}></IonInput>
                    </IonItem>

                    <IonItem>
                        <IonLabel position="stacked" class="label">Password</IonLabel>
                        <IonInput value={password} type="password" class="input-group" onIonChange={e=>setPassword(e.detail.value!)} ></IonInput>
                    </IonItem>

                    <IonItem>
                        <IonLabel>
                            <a href="/register">Register</a>
                        </IonLabel>
                    </IonItem>

                    <IonButton expand="block" color="primary" onClick={handleLogin}>Login</IonButton>

                </IonCardContent>

            </IonCard>
        </div>
    )
}

export default Login;