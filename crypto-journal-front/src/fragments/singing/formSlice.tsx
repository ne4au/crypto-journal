import { createSlice } from '@reduxjs/toolkit';
import SigningType from '../../home/sign/SigningType';

export interface FormState {
    isOpened: boolean,
    activeFormType: SigningType,
}

export const formSlice = createSlice({
    name: 'formState',
    initialState: {
        isOpened: false,
        activeFormType: SigningType.SIGNING_UP,
    },
    reducers: {
        openSignIn: (state: FormState) => {
            state.activeFormType = SigningType.SIGNING_IN;
            state.isOpened = true;
        },
        openSignUp: (state: FormState) => {
            state.activeFormType = SigningType.SIGNING_UP;
            state.isOpened = true;
        },
        changeToSignUp: (state: FormState) => {
            state.activeFormType = SigningType.SIGNING_UP;
        },
        changeToSignIn: (state: FormState) => {
            state.activeFormType = SigningType.SIGNING_IN;
        },
        close: (state: FormState) => { state.isOpened = false; },
    }
});

export const {
    openSignIn,
    openSignUp,
    changeToSignUp,
    changeToSignIn,
    close,
} = formSlice.actions;

export default formSlice.reducer;