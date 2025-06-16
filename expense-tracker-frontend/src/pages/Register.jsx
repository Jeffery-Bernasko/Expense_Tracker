import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register(){
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        password: ''
    });

    const [message, setMessage] = useState('');

    const handleChange = (e) => {
    setFormData({ 
      ...formData, 
      [e.target.name]: e.target.value 
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/users/register', formData);
      setMessage('Registration successful! Redirecting to login...');
      setTimeout(() => navigate('/'), 2000); // redirect to login
    } catch (error) {
      console.error(error);
      setMessage('Error registering user');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <form onSubmit={handleSubmit} className="bg-white p-8 rounded shadow-md w-full max-w-sm">
        <h2 className="text-2xl font-bold mb-4 text-center">Register User</h2>

        <input
          type="text"
          name="name"
          placeholder="Name"
          value={formData.name}
          onChange={handleChange}
          className="w-full mb-4 p-2 border rounded"
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          className="w-full mb-4 p-2 border rounded"
          required
        />

        <button type="submit" className="bg-blue-600 text-white w-full py-2 rounded hover:bg-blue-700">
          Register
        </button>

        {message && (
          <p className="text-sm text-center text-red-500 mt-4">{message}</p>
        )}
      </form>
    </div>
  );
}

export default Register;