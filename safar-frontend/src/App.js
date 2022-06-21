import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import LoginForm from "./components/forms/LoginForm.tsx";
import RegistrationForm from "./components/forms/RegistrationForm.tsx";
import Home from './components/Home.tsx';
function App() {
	

	return (
		// <div className="App">
		//     {getUsers()}
		// </div>
		<>
		
		<Router>

			<Routes>
			
				<Route path="/login" element={<LoginForm />} />
				<Route path="/register" element={<RegistrationForm />} />
				<Route path='/home' element={<Home />} />
			
			</Routes>
			
		</Router>
		</>
	);
}

export default App;
