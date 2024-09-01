import { Transition } from '@headlessui/react';
import { FC, useRef } from 'react';
import { RootState } from '../../fragments/rootReducer';
import { useDispatch, useSelector } from 'react-redux';
import SigningChanging from './SigningChanger';
import SigningType from './SigningType';
import SigningUpForm from './SigningUpForm';
import SigningInForm from './SigningInForm';
import SigningWindowHeader from './SigningWindowHeader';

const SigningContent: FC = () => {
    const activeType: SigningType = useSelector((state: RootState) => state.formState.activeFormType);
    const signInFormFirstField = useRef(null);
    const signUpFormFirstField = useRef(null);

    return (
        <div className='flex flex-col justify-center'>
            <SigningWindowHeader
                activeType={activeType} />
            <SigningChanging
                activeType={activeType}
                signInFormFirstField={signInFormFirstField}
                signUpFormFirstField={signUpFormFirstField}
            />
            <div className='flex flex-col px-6 py-3 mt-3'>
                <div className='h-[300px]' />
                <Transition
                    show={activeType === SigningType.SIGNING_UP}
                >
                    <SigningUpForm firstFieldRef={signUpFormFirstField}
                        classNameStyle='-translate-y-[300px] h-0' />
                </Transition>
                <Transition
                    show={activeType === SigningType.SIGNING_IN}
                >
                    <SigningInForm firstFieldRef={signInFormFirstField}
                        classNameStyle='-translate-y-[300px] h-0'
                    />
                </Transition>
            </div>

        </div>
    );
};

export default SigningContent;